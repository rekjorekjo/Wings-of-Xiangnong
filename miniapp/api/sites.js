import api from './api'

/**
 * shopGetList 
 */
export function shopGetList(data) {
  return api.get('/store/list', data, { login: false })
}

const DEFAULT_LOCATION = {
	latitude: 39.919990,
	longitude: 116.456270
}

const getSafeLocation = (location = {}) => {
	const latitude = Number(location.latitude)
	const longitude = Number(location.longitude)

	if (Number.isFinite(latitude) && Number.isFinite(longitude)) {
		return { latitude, longitude }
	}

	return DEFAULT_LOCATION
}

const getCurrentLocation = () => {
	return new Promise(resolve => {
		uni.getLocation({
			type: 'wgs84',
			success(res) {
				resolve({
					latitude: res.latitude,
					longitude: res.longitude
				})
			},
			fail() {
				resolve(null)
			}
		})
	})
}

export const ensureActiveShop = async(main) => {
	if (main.RESTORE_SESSION) {
		main.RESTORE_SESSION()
	}

	if (main.store && main.store.id) {
		return main.store
	}

	const location = (main.location && main.location.latitude)
		? getSafeLocation(main.location)
		: getSafeLocation(await getCurrentLocation())
	main.SET_LOCATION(location)

	let shopList = []
	try {
		shopList = await shopGetList({
			lat: location.latitude,
			lng: location.longitude,
			kw: '',
			shop_id: 0
		})
	} catch (error) {
		console.warn('[shop] 自动恢复门店失败:', error)
	}

	if (!Array.isArray(shopList) || shopList.length === 0) {
		main.SET_STORE({})
		return null
	}

	const activeShop = shopList.find(shop => shop.status === 1)
	if (!activeShop) {
		main.SET_STORE({})
		return null
	}

	main.SET_STORE(activeShop)
	console.log('[shop] 自动恢复营业门店:', activeShop.name, 'id:', activeShop.id)
	return activeShop
}
