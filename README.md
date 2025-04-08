# ☀️ Weather Diary API

Spring Boot 기반의 날씨 일기 API 프로젝트입니다.  
외부 OpenWeatherMap API를 통해 날씨 정보를 가져오고, 사용자 일기를 함께 저장하고 조회할 수 있습니다.

---

## 📌 주요 기능

- ☁️ 외부 API로 현재 날씨 정보 수집  
- 📝 사용자의 일기와 함께 날씨 데이터를 저장  
- 🔍 날짜별 일기 목록 조회  
- 🧪 간단한 JDBC 기반 메모 기능 (학습용)

---

## ⚙️ 사용 기술

| 기술        | 설명 |
|-------------|------|
| Java 21     | 언어 버전 |
| Spring Boot | 웹 서버, DI, RestController 등 |
| JPA         | Diary 도메인 DB 저장 |
| JDBC        | Memo 저장 예제 |
| MySQL       | DB 저장소 (or H2 대체 가능) |
| JSON Simple | 외부 API JSON 파싱 |
| Lombok      | 코드 간결화 (Getter/Setter 등)

---

## 🚀 실행 방법

1. 프로젝트 클론
```bash
git clone https://github.com/your-id/weather-diary.git
cd weather-diary
```

2. `application.properties` 또는 `application.yml`에 OpenWeatherMap API 키 추가
```properties
openweathermap.key=YOUR_API_KEY
spring.datasource.url=jdbc:mysql://localhost:3306/your_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

3. 실행
```bash
./gradlew bootRun
```

---

## 📁 프로젝트 구조

```
src/
├── controller       # REST API 컨트롤러
├── domain           # Diary, Memo 엔티티
├── repository       # DiaryRepository (JPA), JdbcMemoRepository
├── service          # 비즈니스 로직, 날씨 API 연동
└── resources        # application.properties 등 설정
```

---

## 🧪 주요 코드 예시

### 🔸 일기 생성 API (POST `/create/diary`)
```java
@PostMapping("/create/diary")
void createDiary(@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate date,
                 @RequestBody String text) {
    diaryService.createDiary(date, text);
}
```

### 🔸 Diary 저장 서비스
```java
public void createDiary(LocalDate date, String text) {
    String weatherData = getWeatherString();
    Map<String,Object> parsedWeather = parsedWeather(weatherData);

    Diary nowDiary = new Diary();
    nowDiary.setWeather(parsedWeather.get("main").toString());
    nowDiary.setIcon(parsedWeather.get("icon").toString());
    nowDiary.setTemperature((Double) parsedWeather.get("temp"));
    nowDiary.setText(text);
    nowDiary.setDate(date);
    diaryRepository.save(nowDiary);
}
```

---

## 📡 외부 API 연동

OpenWeatherMap API로 서울 지역의 날씨 정보를 받아옵니다.

```java
String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=seoul&appid=" + apiKey;
HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
connection.setRequestMethod("GET");
```

---

## 🗃️ Memo 기능 (JDBC 예제)

```java
public Memo save(Memo memo) {
    String sql = "INSERT INTO memo VALUES (?, ?)";
    jdbcTemplate.update(sql, memo.getId(), memo.getText());
    return memo;
}
```

---

## ✅ 테스트

- 테스트는 JUnit + Mockito 기반
- 향후 Postman Collection으로 API 테스트 자동화 예정

---

## 🙋🏻 프로젝트 목적

- Spring Boot의 구조 이해
- 외부 API 사용과 JSON 파싱
- JPA와 JDBC의 차이점 학습
- 포트폴리오용 RESTful API 백엔드 앱 제작

---

## 💬 문의

프로젝트 관련 문의는 gleewithajoy@gmail.com 또는 GitHub 이슈를 이용해주세요.
```
