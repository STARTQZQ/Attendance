//是否选择类型

function checkWorkClass(rule, value, callback){
  if (value == "") {
    callback(new Error("请选择"))
  }
else {
    callback()
  }

}

function checkBegin(rule, value, callback){
  if (value == "") {
    callback(new Error("请选择"))
  }
  else {
    callback()
  }

}
function checkEnd(rule, value, callback){
  if (value == "") {
    callback(new Error("请选择"))
  }
  else {
    callback()
  }

}

function checkReason(rule, value, callback){
  if (value == "") {
    callback(new Error("请选择"))
  }
  else {
    callback()
  }

}



