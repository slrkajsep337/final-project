# π¦ Final Project [ Mutsa SNS ]

## πνλ‘μ νΈ κ°μ 
- Spring Boot Jpa λ₯Ό μ΄μ©νμ¬ SNS μλΉμ€ κ΅¬ννκΈ° 

## π κ°λ° κΈ°κ° 
- 22/12/20 ~
## βοΈ Tech stacks
<div align="center">
	<img src="https://img.shields.io/badge/Java-007396?style=flat&logo=Java&logoColor=white" />
    <img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=flat&logo=Spring Boot&logoColor=white" />
    <img src="https://img.shields.io/badge/Spring Security-6DB33F?style=flat&logo=Spring Security&logoColor=white" />
    <img src="https://img.shields.io/badge/IntelliJ Ultimate-FF007F?style=flat&logo=IntelliJ IDEA&logoColor=white" />
    </br>
    <img src="https://img.shields.io/badge/MySQL Workbench-4479A1?style=flat&logo=MySQL&logoColor=white" />
    <img src="https://img.shields.io/badge/AWS EC2-FF9900?style=flat&logo=Amazon EC2&logoColor=white" />
    </br>
    <img src="https://img.shields.io/badge/Docker-2496ED?style=flat&logo=Docker&logoColor=white" />
    <img src="https://img.shields.io/badge/GitHub-181717?style=flat&logo=GitHub&logoColor=white" />
    <img src="https://img.shields.io/badge/GitLab-181717?style=flat&logo=GitLab&logoColor=white" />
    <img src="https://img.shields.io/badge/Swagger-85EA2D?style=flat&logo=Swagger&logoColor=white" />
</div>

## π Swagger
http://ec2-3-34-4-22.ap-northeast-2.compute.amazonaws.com:8080/swagger-ui/

## π Architecture
<img src="img/img1.png" width="530" height="130">

## β Erd
<img src="img/snsErd.png" width="400" height="550">


## βοΈ Endpoints (/api/v1)
<img src="img/endpoints.png" width="500" height="400">

## π μ€κ° νκ³ 
- `CI/CD`λ μ§μ  μ¬μ©ν΄λ³΄λ μμνλ κ²λ³΄λ€ ν¨μ¬ λ νΈλ¦¬νλ€. μ΄μ μ λμ»€μ μ§μ  λ°°ν¬νκ³  μ€λ₯λ₯Ό ν΄κ²°νλ λλ₯Ό μκ°νλ©΄ 
νμ€ν κ°λ° μ΄μΈμ λ€λ₯Έ μΌμ μ€μ¬μ£Όμ΄ κΈ°λ₯ κ΅¬νμ λ μκ°μ μ¬μ©ν  μ μμλ€. 
- User~Post κΈ°λ₯ κ΅¬ν λ μμλ³΄λ€ μκ°μ΄ μ΄λ°νκΈ° λλ¬Έμ, κ·Έ μ΄νμλ κΈ°λ₯μ μ°μ μΌλ‘ κ΅¬ννκ³  νμ λ¦¬ν©ν λ§μ νλ λ°©μμΌλ‘ μ§νν΄λ³΄μλ€.
νμ§λ§ λ¦¬ν©ν λ§μ μκ°μ΄ λλ¬΄ λ§μ΄ λ€μ΄μ μ΄κ²λ κ·Έλ κ² μ’μ λ°©λ²μ μλμλ κ² κ°λ€. κ·Έλ μ§λ§ μ€κ³λ₯Ό κΌΌκΌΌν ν΄λ κ²°κ΅­ κ΅¬ν μ€μ λ¬Έμ λ μκΈ°κΈ° λλ¬Έμ
μ λΉν μ€κ³μ κΈ°μ€μ μΈμ°λ κ²μ΄ μ€μν κ² κ°λ€. κ³μν΄μ νλ‘μ νΈλ₯Ό μ§ννλ€λ³΄λ©΄ μ΄λμ λ μ€κ³μ κΈ°μ€λ μκΈ°κ³  μ½λ© ν¨ν΄λ μκ²¨μ ν¨μ¨μ΄ μ¬λΌκ° κ²μ΄λΌκ³  μκ°νλ€.
- μλ‘μ΄ κΈ°λ₯μ κ΅¬νν  λλ§λ€ λΉμ°ν μ¬κΈ°κ³  `controller`, `service`λ₯Ό λλμ΄μ μ½λλ₯Ό μμ±νλλ° likeμ alarm κΈ°λ₯μ κ΅¬ννλ©΄μ 
κΌ­ λͺ¨λ λλμ΄ μ€μΌ νλμ§ μλ¬Έμ΄ μκ²Όλ€. 
- λΉμ·ν λ§₯λ½μΌλ‘ `dto`λ₯Ό λλλ κΈ°μ€μ λν΄μλ λ‘μ§μ μ§λ©΄μ λμμμ΄ κ³ λ―Όνλλ°(νλκ° κ°μλ° κΌ­ λλμ΄μ€μΌ ν μ§) μ΄ λΆλΆμ λν΄μ μ°μ μ μΆνμ νμ₯μ±μ μν΄ 
μ΅λν λλμ΄μ μ€κ³νλκ² μ’λ€λ κ²°λ‘ μ λ΄λ Έλ€. 
- μ²μ κ΅¬νμ ν  λ post μμ , comment μμ  κ³Ό κ°μ΄ νΉμ  νλμ κ°λ§ updateλ₯Ό ν΄μ€μΌνλ κ²½μ° entityμ setterλ₯Ό λΆμ¬μ setμΌλ‘ κ°μ λ£μ΄μ€¬λλ° 
`entityμλ setterμ¬μ©μ μ§μ`νλΌλ λ΄μ©μ λ³΄κ² λμκ³ (μμΈν λ΄μ©μ λΈλ‘κ·Έμ μ λ¦¬) entityμ ν¨μλ₯Ό λ§λ€μ΄μ£Όλ μμΌλ‘ λ¦¬ν©ν λ§ ν΄μ£Όμλ€.
- `soft delete`λ κ΅μ₯ν νΈλ¦¬ν jpaμ κΈ°λ₯μ΄μμ§λ§ timezoneμ΄ asiaμ λ§μΆ°μ Έμμ§ μμ dbμͺ½μμ timezone μ€μ μ ν΄μ€μΌνλ€. 
- λ±λ‘,μμ ,μ­μ  μκ°μ `auditing`μ μ μ©ν΄ λ¦¬ν©ν λ§ νλλ° μκ° μ μ₯ν΄μ£Όλ μ½λκ° νμ μμ΄μ Έμ λ‘μ§μ΄ κ°κ²°ν΄μ§κ³  λλ¬΄ νΈλ¦¬νλ€.

β‘οΈμμ§ `spring security`λΆλΆμ μ΄ν΄κ° νΉν λ§μ΄ λΆμ‘±νλ€. κ³΅λΆλ₯Ό λ νκ³  ui κ΅¬νκ³Ό ν¨κ» user role κΈ°λ₯λ κ΅¬νν  μμ μ΄λ€. 

