let index = 0

export function isNull(value) {
  return (value === null) || (value === undefined)
}

export function isNotNull(value) {
  return (value !== null) && (value !== undefined)
}

export function isEmptyStr(str) {
  return (str === undefined) || (!str && (str !== 0) && (str !== '0')) || (!/[^\s]/.test(str))
}

export const generateId = function() {
  return (new Date().getTime() % (1000 * 60 * 60 * 24) + index++).toString(36)
}

export const deepClone = function(data, apply = function(data, label) {
  return data[label]
}) {
  if (!data || typeof data !== 'object') {
    return data
  }
  if (Array.isArray(data)) {
    return data.map(_ => deepClone(_, apply))
  }
  const newData = {}
  const dataKeys = Object.keys(data)
  dataKeys.forEach(label => {
    const value = apply(data, label)
    newData[label] = deepClone(value, apply)
  })
  return newData
}


