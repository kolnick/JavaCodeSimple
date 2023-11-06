
# 用户控制器
## 获取用户列表
**URL:** `http://localhost:17150/user/[]`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=utf-8`

**Description:** 获取用户列表





**Request-example:**
```
curl -X GET -i http://localhost:17150/user/[]
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
id|int64|id|-
name|string|姓名|-
age|int32|年龄|-

**Response-example:**
```
[
  {
    "id": 891,
    "name": "george.corkery",
    "age": 2
  }
]
```

## 
**URL:** `http://localhost:17150/user/`

**Type:** `POST`


**Content-Type:** `application/x-www-form-urlencoded;charset=utf-8`

**Description:** 



**Query-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
id|int64|id|false|-
name|string|姓名|false|-
age|int32|年龄|false|-


**Request-example:**
```
curl -X POST -i http://localhost:17150/user/ --data 'name=george.corkery&age=2&id=436'
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
code|int32|错误码|-
msg|string|错误消息|-
data|object|数据|-
└─id|int64|id|-
└─name|string|姓名|-
└─age|int32|年龄|-

**Response-example:**
```
{
  "code": 362,
  "msg": "4f5luz",
  "data": {
    "id": 832,
    "name": "george.corkery",
    "age": 2
  }
}
```

## 获取某个用户
**URL:** `http://localhost:17150/user/{id}`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=utf-8`

**Description:** 获取某个用户


**Path-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
id|int64|用户ID|false|-



**Request-example:**
```
curl -X GET -i http://localhost:17150/user/910
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
id|int64|id|-
name|string|姓名|-
age|int32|年龄|-

**Response-example:**
```
{
  "id": 44,
  "name": "george.corkery",
  "age": 2
}
```

## 
**URL:** `http://localhost:17150/user/{id}`

**Type:** `PUT`


**Content-Type:** `application/x-www-form-urlencoded;charset=utf-8`

**Description:** 


**Path-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
id|int64|No comments found.|true|-

**Query-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
id|int64|id|false|-
name|string|姓名|false|-
age|int32|年龄|false|-


**Request-example:**
```
curl -X PUT -i http://localhost:17150/user/845 --data 'name=george.corkery&id=343&age=2'
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
code|int32|错误码|-
msg|string|错误消息|-
data|object|数据|-
└─id|int64|id|-
└─name|string|姓名|-
└─age|int32|年龄|-

**Response-example:**
```
{
  "code": 690,
  "msg": "vwsnym",
  "data": {
    "id": 790,
    "name": "george.corkery",
    "age": 2
  }
}
```

## 
**URL:** `http://localhost:17150/user/{id}`

**Type:** `DELETE`


**Content-Type:** `application/x-www-form-urlencoded;charset=utf-8`

**Description:** 


**Path-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
id|int64|No comments found.|true|-



**Request-example:**
```
curl -X DELETE -i http://localhost:17150/user/996
```

**Response-example:**
```
string
```

## 
**URL:** `http://localhost:17150/user/ignoreMe/{id}`

**Type:** `DELETE`


**Content-Type:** `application/x-www-form-urlencoded;charset=utf-8`

**Description:** 


**Path-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
id|int64|No comments found.|true|-



**Request-example:**
```
curl -X DELETE -i http://localhost:17150/user/ignoreMe/588
```

**Response-example:**
```
string
```

