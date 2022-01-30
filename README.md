Тестовое задание - обработка оценок группы студентов

localhost:8080

### **Libraries:**

JDK 11

Maven

Spring Boot

Junit

### **Run local with maven:**

mvn clean package spring-boot:run

### **API:**

**GET** {host}/v1/students

**GET** {host}/v1/students/{subject}

**GET** {host}/v1/students/makeCSV

Для фильтрации по предмету передать RequestParam - subjectName

**POST** {host}/v1/students/addStudent/{studentName} 

**POST** {host}/v1/students/addMark - take body: NewMarkRequest with fields studentName, mark, subjectName, studentId  

Поле studentId в случае одинаковых фамилий выставлять оценку правильному студенту


