
# 
## 
**URL:** `http://localhost:17150/messages`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=utf-8`

**Description:** 





**Request-example:**
```
curl -X GET -i http://localhost:17150/messages
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
id|int64|No comments found.|-
text|string|No comments found.|-
summary|string|No comments found.|-
createDate|string|No comments found.|-

**Response-example:**
```
[
  {
    "id": 178,
    "text": "v114uc",
    "summary": "cc6u0a",
    "createDate": "2023-01-09"
  }
]
```

## 
**URL:** `http://localhost:17150/message`

**Type:** `PUT`


**Content-Type:** `application/x-www-form-urlencoded;charset=utf-8`

**Description:** 



**Query-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
id|int64|No comments found.|false|-
text|string|No comments found.|false|-
summary|string|No comments found.|false|-
createDate|string|No comments found.|false|-


**Request-example:**
```
curl -X PUT -i http://localhost:17150/message --data 'summary=p8d3lz&id=853&text=l9cxfw&createDate=2023-01-09'
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
id|int64|No comments found.|-
text|string|No comments found.|-
summary|string|No comments found.|-
createDate|string|No comments found.|-

**Response-example:**
```
{
  "id": 723,
  "text": "m8r6rg",
  "summary": "fucgds",
  "createDate": "2023-01-09"
}
```

## 
**URL:** `http://localhost:17150/message/text`

**Type:** `PATCH`


**Content-Type:** `application/x-www-form-urlencoded;charset=utf-8`

**Description:** 



**Query-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
id|int64|No comments found.|false|-
text|string|No comments found.|false|-
summary|string|No comments found.|false|-
createDate|string|No comments found.|false|-


**Request-example:**
```
curl -X PATCH -i http://localhost:17150/message/text?text=9tkvkl&id=174&summary=sr6d3e&createDate=2023-01-09
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
code|int32|错误码|-
msg|string|错误消息|-
data|object|数据|-
└─id|int64|No comments found.|-
└─text|string|No comments found.|-
└─summary|string|No comments found.|-
└─createDate|string|No comments found.|-

**Response-example:**
```
{
  "code": 531,
  "msg": "b38eze",
  "data": {
    "id": 883,
    "text": "6dg8zn",
    "summary": "jjhlvq",
    "createDate": "2023-01-09"
  }
}
```

## 
**URL:** `http://localhost:17150/message/{id}`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=utf-8`

**Description:** 


**Path-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
id|int64|No comments found.|true|-



**Request-example:**
```
curl -X GET -i http://localhost:17150/message/718
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
id|int64|No comments found.|-
text|string|No comments found.|-
summary|string|No comments found.|-
createDate|string|No comments found.|-

**Response-example:**
```
{
  "id": 224,
  "text": "92cook",
  "summary": "367g3f",
  "createDate": "2023-01-09"
}
```

## 
**URL:** `http://localhost:17150/message/{id}`

**Type:** `DELETE`


**Content-Type:** `application/x-www-form-urlencoded;charset=utf-8`

**Description:** 


**Path-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
id|int64|No comments found.|true|-



**Request-example:**
```
curl -X DELETE -i http://localhost:17150/message/173
```

**Response-example:**
```
Return void.
```

