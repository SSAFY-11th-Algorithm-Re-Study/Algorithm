# SSAFY 11기 다시 시작하는 알고리즘 스터디

## 스터디 멤버

| 깃&nbsp;허&nbsp;브 | [hj&nbsp;-&nbsp;k66](https://github.com/hj-k66) |      [Jungjaehee](https://github.com/Jungjaehee)       | [honggyeonghyeon](https://github.com/honggyeonghyeon) |
| :----------------: | :---------------------------------------------------: | :---------------------------------------------: | :----------------------------------------------------: | :---------------------------------------------------: |
|     백&nbsp;준     |    [hjx06](https://solved.ac/profile/hjx06)     | [wjdwogml5890](https://solved.ac/profile/wjdwogml5890) |  [hyeon21owo](https://solved.ac/profile/hyeon21owo)   |

## 규칙

```
1. 문제 출제는 마감일 2일전까지 출제 (수 마감 → 월까지 출제)
2. 코드 제출은 일주일 3회(월, 수, 금)이며 마감일 11:59까지 인정
3. 3회 이상 미제출 시, 방출
4. 플랫폼은 백준, SWEA, 프로그래머스
5. 문제 선택은 희정 -> 재희 -> 경현 순
6. 각자 코드를 github에 올리기
7. 궁금한 점을 올리거나 다른 사람 코드에서 궁금한 부분을 질문해주세요!
```

## 깃허브 관련 주의할 점

```
1. 개인 fork한 저장소에서 브랜치는 자유롭게 만들어도 되지만 pull-request시에는 중앙 저장소와 맞아야 함
	- 하나의 브랜치로 쓰는것을 추천
2. 중앙 저장소는 반드시 main 브랜치만 사용하고 master 브랜치나 다른 브랜치 생성 X
3. (매우 중요) 깃허브 commit이나 merge가 안될 시 혼자 해결하려 하지 말고 제발 공유할 것
```

## 저장소 연결

```
1. 현재 레포지토리(중앙저장소)를 개인 저장소로 fork (원격 저장소)
2. fork한 저장소를 clone
	- ex) git clone https://github.com/Junajaehee/Algorithm_Study_2.git
3. git remote -v 로 origin에 fork한 저장소 확인
4. 중앙 저장소 추가 (upstream)
	- git remote add upstream https://github.com/SSAFY-11th-Algorithm-Re-Study/Algorithm.git
```

## 커밋 방법

```
1. 중앙 저장소의 자료를 pull (반드시 push전에 pull 먼저 해야함)
	- git pull upstream main
2. 로컬 저장소에서 staging 영역에 commit할 파일 올리기
	- git add . (작업한 파일 전체 올라감)
3. commit
	- git commit -m "쓸말"
4. 원격 저장소에 push
	- git push origin main
```

## Merge 방법

```
1. fork한 원격 저장소에 들어가서 Pull request 클릭
2. new pull request 클릭
3. 원격 저장소에서 중앙 저장소의 main으로 보내는지 확인하고 merge
	- pull을 먼저 하지 않아 중앙저장소와 원격저장소의 파일이 맞지 않으면 머지 불가
```
