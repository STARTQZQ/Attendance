// 查询列表页接口
const getClockPage = (params) => {
  return $axios({
    url: '/clock/page',
    method: 'get',
    params
  })
}
// 删除接口
const deleteClock = (ids) => {
  return $axios({
    url: '/clock',
    method: 'delete',
    params: { ids }
  })
}

