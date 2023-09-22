function getMyMemberList (params) {
  return $axios({
    url: '/vacation/myPage',
    method: 'get',
    params
  })
}




// 新增接口
const addVacation = (params) => {
  return $axios({
    url: '/vacation',
    method: 'post',
    data: { ...params }
  })
}


// 删除接口
const deleteTravel = (ids) => {
  return $axios({
    url: '/vacation',
    method: 'delete',
    params: { ids }
  })
}

