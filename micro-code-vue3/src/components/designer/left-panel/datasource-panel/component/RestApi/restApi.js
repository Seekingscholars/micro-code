// import request from "@/utils/request";

// 查询api请求列表
export function listRestApi(params) {
  return request({
    url: "/sys/lowcode/restApi/list",
    method: "get",
    params: params
  });
}

// 查询api请求详细
export function getRestApi(id) {
  return request({
    url: "/sys/lowcode/restApi/" + id,
    method: "get"
  });
}

// 新增api请求
export function addRestApi(data) {
  return request({
    url: "/sys/lowcode/restApi",
    method: "post",
    data: data
  });
}

// 修改api请求
export function updateRestApi(data) {
  return request({
    url: "/sys/lowcode/restApi",
    method: "put",
    data: data
  });
}

// 删除api请求
export function delRestApi(id) {
  return request({
    url: "/sys/lowcode/restApi/" + id,
    method: "delete"
  });
}

export const REQUEST_METHOD = [
  { label: "GET", value: "GET" },
  { label: "POST", value: "POST" },
  {
    label: "PUT",
    value: "PUT"
  },
  { label: "DELETE", value: "DELETE" }
];
