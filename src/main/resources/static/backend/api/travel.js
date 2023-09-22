// 查询列表接口
const getTravelPage = (params) => {
  return $axios({
    url: '/travel/page',
    method: 'get',
    params
  })
}

// 删除接口
const deleteTravel = (ids) => {
  return $axios({
    url: '/travel',
    method: 'delete',
    params: { ids }
  })
}

// 修改接口
const editTravel = (params) => {
  return $axios({
    url: '/travel',
    method: 'put',
    data: { ...params }
  })
}

// 新增接口
const addTravel = (params) => {
  return $axios({
    url: '/travel',
    method: 'post',
    data: { ...params }
  })
}

// 查询详情
const queryTravelById = (id) => {
  return $axios({
    url: `/travel/${id}`,
    method: 'get'
  })
}

// 获取菜品分类列表
const getTravelList = (params) => {
  return $axios({
    url: '/travel/list',
    method: 'get',
    params
  })
}

// 查菜品列表的接口
const queryTravelList = (params) => {
  return $axios({
    url: '/travel/list',
    method: 'get',
    params
  })
}

// 文件down预览
const commonDownload = (params) => {
  return $axios({
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    url: '/common/download',
    method: 'get',
    params
  })
}

// 起售停售---批量起售停售接口
const travelStatusByStatus = (params) => {
  return $axios({
    // /${params.agree}
    url: '/travel/agree',
    method: 'post',
    params: { ids: params.id }
  })
}