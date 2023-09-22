function loginApi(data) {
  return $axios({
    'url': '/admins/login',
    'method': 'post',
    data
  })
}

function logoutApi(){
  return $axios({
    'url': '/admins/logout',
    'method': 'post',
  })
}
