
// 新增---打卡数据
function addClock (params) {
  return $axios({
    url: '/clock',
    method: 'post',
    data: { ...params }
  })
}

// 新增---打卡数据
const getClockData = (idcard) => {
  return $axios({
    url: `/clock/${idcard}`,
    method: 'get',
  })
}

function getClockList (params) {
  return $axios({
    url: '/clock/myPage',
    method: 'get',
    params
  })
}




