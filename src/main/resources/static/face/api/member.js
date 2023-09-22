function getMemberList (params) {
  return $axios({
    url: '/staff/page',
    method: 'get',
    params
  })
}



// 新增---添加员工
function addEmployee (params) {
  return $axios({
    url: '/staff',
    method: 'post',
    data: { ...params }
  })
}

// 修改---添加员工
function editEmployee (params) {
  return $axios({
    url: '/staff',
    method: 'put',
    data: { ...params }
  })
}

// 修改页面反查详情接口
function queryEmployeeById (id) {
  return $axios({
    url: `/staff/${id}`,
    method: 'get'
  })
}