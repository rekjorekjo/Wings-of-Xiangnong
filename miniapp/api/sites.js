import api from './api'

/**
 * shopGetList 
 */
export function shopGetList(data) {
  return api.get('/store/list', data, { login: false })
}
