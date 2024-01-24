# 웹 프로젝트:
> 목표: 그날 입을 옷을 추천해주고 싶은 사이트

## Log
### 2024/01
**16 :**
[이클립스와 깃 연동 시 참고한 티스토리](https://mollangpiu.tistory.com/309)   
아니다... 그냥 Git Bash를 쓰도록 하자... 나는 아직 이게 어떻게 돌아가는지도 모른다...   
   
*Problem*   
브랜치 이름이 master일 경우 머지가 되지 않는다. (추정)   
모든 것이 힘겹다...   
   
<hr>   
   
**17 :**   
git remote   
add name link : new repo add   
   
*if one name, two repo = upload one time two repo*   
> git remote set-url name --push --add link.git   
*remove*   
> git remote set-url name --push --delete link.git   


**18 :**   
초단기 실황/예보, 단기 예보 VO 생성   
*xml parsing*   
> String으로 받아온 request 값을 Document 형식으로 변환, NodeList return 완료
> 내일은 각 값을 VO에 대입해서 현재 날씨를 출력해볼 예정
   
   
**19 :**   
xml parse를 할 때, childNode의 childNode를 찾기 위해 추가 작업을 더 해주어야 한다.   
나는 xml값이 날아오는데 VO에 들어가지는 않는 이유를 찾느라 오늘 하루를 다 날렸다.  
   
   
**20-21 :**   
20일은 쉰 게 아니다. 하다가 보니 21일 오전 1시가 되었을 뿐이다.   
가공하는 일이 가장 힘들다...   
외에도 단순히 개행문자 넣는 일이 이렇게 번거로울 줄 몰랐다. *replaceAll*   
초단기실황은 n시 20분쯤까지 n-1시의 정보를 받아와서, baseTime(실황 시각)값을 밑장빼기했다.   
   
   
**23-24 :**
geolocation으로 좌표값을 받아왔다!!!   
위/경도 값 > 그리드값으로 변환하는 메서드 추가   
script로 받은 값을 servlet으로 처리하기 위해 form을 submit하고, 페이지를 리로딩   
==무한 새로고침 문제 발생   
form에 좌표를 받아왔다는 의미의 값 추가해서 해결   

   
**25 :**   
gitignore을 써보고 싶었지만 장렬하게 Reset했다.   
revert를 하고 싶었는데 충돌을 해결하지 못했다.   
DAO의 쓸데없는 반복문을 줄이는 선에서 그쳤다.   
