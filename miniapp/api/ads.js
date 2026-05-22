import api from './api'

/**
 * menuAds 
 */
export function menuAds(data) {
  return api.get('/ad/list', data, { login: false })
}
