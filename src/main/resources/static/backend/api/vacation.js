// 查询列表接口
const getDishPage = (params) => {
  return $axios({
    url: '/vacation/page',
    method: 'get',
    params
  })
}

// 删除接口
const deleteDish = (ids) => {
  return $axios({
    url: '/vacation',
    method: 'delete',
    params: { ids }
  })
}

// 修改接口
const editDish = (params) => {
  return $axios({
    url: '/vacation',
    method: 'put',
    data: { ...params }
  })
}

// 新增接口
const addDish = (params) => {
  return $axios({
    url: '/vacation',
    method: 'post',
    data: { ...params }
  })
}

// 查询详情
const queryDishById = (id) => {
  return $axios({
    url: `/vacation/${id}`,
    method: 'get'
  })
}

// 获取分类列表
const getCategoryList = (params) => {
  return $axios({
    url: '/category/list',
    method: 'get',
    params
  })
}

// 查列表的接口
const queryDishList = (params) => {
  return $axios({
    url: '/vacation/list',
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

// -批量接口
const dishStatusByStatus = (params) => {
  return $axios({
  // /${params.agree}
    url: '/vacation/agree',
    method: 'post',
    params: { ids: params.id }
  })
}