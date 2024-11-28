## 실행

이 프로젝트는 **Docker**를 통해 개발 환경이 설정되어 있습니다. 따라서 프로젝트를 실행하려면 Docker가 설치되어 있어야 합니다.

### 1. Docker 설치

프로젝트를 실행하기 전에 **Docker**를 설치해야 합니다.

### 2. 프로젝트 실행

Docker를 실행한 후, docker-compose.yml 파일이 있는 디렉토리에서 아래 명령어를 입력하여 Docker Compose를 실행합니다.

```bash
docker-compose up -d
```

### 3. 테스트 환경

테스트는 실제 서비스와 유사한 환경에서 진행되도록 TestContainers를 사용합니다.

따라서, 테스트를 실행하기 전에 Docker를 실행한 후 테스트를 진행하는 것이 권장됩니다.

## API 문서

### 사용자 수정

- **URL:** `/user/update`
- **메서드:** `PUT`
- **설명:** 사용자의 정보를 수정합니다.
- **파라미터:**
    - `userId` (String): 수정할 사용자의 ID
    - `updateName` (String): 수정할 사용자 이름
- **응답:** 200 OK - 수정된 사용자 정보 (UserUpdateDto)

<details>
<summary>UserUpdateDto</summary>

```json
{
  "userId": "string",
  "userName": "string"
}
```
</details>

---

### 사용자 삭제
- **URL:** `/user/delete`
- **메서드:** `DELETE`
- **설명:** 사용자를 삭제합니다.
- **파라미터:**
    - `userId` (String): 삭제할 사용자의 ID.
- **응답:**
    - **200 OK:** 삭제된 사용자 정보 (`UserDetailDto`).

<details>
<summary>UserDetailDto</summary>

```json
{
  "userId": "string",
  "userName": "string",
  "userAuth": "string"
}
```
</details>

---

### 사용자 등록
- **URL:** `/user/create`
- **메서드:** `POST`
- **설명:** 새로운 사용자를 등록합니다.
- **파라미터:**
    - `userCreateDto` (JSON): 새로운 사용자 정보.
- **응답:**
    - **200 OK:** 등록된 사용자 정보 (`UserInsertDto`).

<details>
<summary>UserCreateDto</summary>

```json
{
  "userId": "string",
  "userPassword": "string",
  "userName": "string"
}
```
</details>

<details>
<summary>UserInsertDto</summary>

```json
{
  "userId": "string",
  "userName": "string"
}
```
</details>

---

### 사용자 ID로 조회
- **URL:** `/user/find/id`
- **메서드:** `GET`
- **설명:** 사용자 ID로 사용자를 조회합니다.
- **파라미터:**
    - `userId` (String): 조회할 사용자의 ID.
- **응답:**
    - **200 OK:** 조회된 사용자 정보 (`UserDetailDto`).

<details>
<summary>UserDetailDto</summary>

```json
{
  "userId": "string",
  "userName": "string",
  "userAuth": "string"
}
```
</details>

---

### 사용자 이름으로 조회
- **URL:** `/user/find/name`
- **메서드:** `GET`
- **설명:** 사용자 이름으로 사용자를 조회합니다.
- **파라미터:**
    - `userName` (String): 조회할 사용자 이름.
- **응답:**
    - **200 OK:** 조회된 사용자 정보 목록 (`List<UserDetailDto>`).

<details>
<summary>UserDetailDto</summary>

```json
{
  "userId": "string",
  "userName": "string",
  "userAuth": "string"
}
```
</details>
