function getTravelList (params) {
  return $axios({
    url: '/travel/myPage',
    method: 'get',
    params
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


// 删除接口
const deleteTravel = (ids) => {
  return $axios({
    url: '/travel',
    method: 'delete',
    params: { ids }
  })
}