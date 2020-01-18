# Polymorphic BST 구현 2015.10

OOP 2 수업에서 Polymorphic Binary Search Tree를 구현하였습니다.

---

## <프로젝트 개요>

- 개발인원: 1명 (본인)
- 개발기간: 2015.10.18 ~ 2015.11.02 (16일)
- 개발환경: Eclipse
- 개발언어: JAVA
- 세부사항: Polymorphic Model & Singleton 사용
    - Polymorphic Tree Node
        - Loop 및 Array / Arraylist 사용 금지, 트리 노드끼리 이어져있는 구조를 재귀적으로 탐색하는 방법을 사용해야함
        - Node를 Null을 사용하지 않음
    - EmptyTree인지 NonEmptyTree인지 알아내려고 getClass 및 instanceOf 사용하는 것 금지
        - 대신에 무조건 Exception Class로 처리
            - EmptyTree에서 TreeIsEmptyException을 throw함.
            - PolymorphicBST에서 TreeIsEmptyException을 catch함.
    - Singleton Model
        - EmptyTree는 무조건 하나 (key, value를 홀드하지 않기 때문에)
    - Key는 comparable object
        - 같은 Key값을 가진 노드는 단 하나: 만약 같은 값이 들어오면 가장 최근의 트리로 대체됨

- Junit으로 테스트 한 모습

        @Test
        public void testDelete2() {
        	PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
        	ptree.put(20, "Twenty");
        	ptree.put(10, "Ten");
        	ptree.put(50, "Fifty");
        	ptree.put(40, "Fourty");
        	ptree.put(60, "Sixty");
        	ptree.put(45, "FourtyFive");
        	ptree.put(44, "FourtyFour");
        	ptree.remove(60);
        	assertEquals(Integer.valueOf(50), ptree.getMax());
        	PlaceKeysValuesInArrayLists<Integer, String> task = 
        			new PlaceKeysValuesInArrayLists<Integer, String>();
        	//task에 ptree의 key,value를 집어넣음
        	ptree.inorderTraversal(task);
        	assertEquals("[10, 20, 40, 44, 45, 50]", task.getKeys().toString());
        }

---

## <프로젝트 구조>

<img src="https://s3.us-west-2.amazonaws.com/secure.notion-static.com/660b77fa-300f-4ce8-b8e9-a939ccec04bc/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=ASIAT73L2G45M26JGNFZ%2F20200118%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20200118T143420Z&X-Amz-Expires=86400&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEDIaCXVzLXdlc3QtMiJHMEUCIQDKvZHVKJJHamU65tqtKiPW2ykVWpqrNxTV7bn9Ge%2F3IAIgZcOxiHV%2BLCePgEh4lApD5Er0TJtpxHbZzzUvQpgqif8qvQMIu%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FARAAGgwyNzQ1NjcxNDkzNzAiDGZbEzbER1pKTXnmWyqRA7EQAVJZv3vXm22I%2FVZ3TqZKPSZ98VP7EjaiLHDsxnB3Pe0zJ9nOoK1e8vzAk7VieWkn7TMFeUnswGOjE0NENoPQpocHY9ifA0%2FXpkd5q0PcFeeD3XOvJeJ26E%2FsDfzrpAsFIOgql9e3bwqL6L%2FIjrqE5RP5i1w7pMXywBLSxrH4r1aggggbRLyVgrW%2FPPa8gwqKMZW%2Fpd2swyIPwHB%2BAb%2BQIcpJeOAPkSDrwjkQmNJOgWbPu6O23Eh%2FsfwdUYyYLHbDT3ScQ9aODBR2ZhZ%2BZMQDZK6lV95iFj8pKrSI9EfsKEPYXF2VEq3jI7YVCBMWOd32IjpaWqCxc%2BXvZ6%2F5pOOEuGDlmKdvz7u9dOAb1nEm%2BhqE1aPllHIHSN35J%2BxixTDeWK6x%2B9OQ8GHArWyYcJNYsDYDV0UXWZiK4FBqywkz2%2FjlnhFOKH0D8vb1BoK7034T9c16HwjIof0SaqDlNJYAvDHPJ1DqlQ2arTAg4Z%2FZZUQDl8D9D2asswNoOaK%2FdwfnJ3wrBY3aeeQT2428AInDMN%2Bri%2FEFOusBmLre5bJffx9GEqC4xXmERjKQfrfnWUF9Z8Y%2Fg4CO5hovxnObVn0FuxMWRMaR2qc3jkpB9x%2FIDw1SXCrDL6v9CVh3pGvQEHx0h%2Fp7HoTl72Kw8rqNVlge0z0Jc%2FlHIp0%2B5Eqcbb78wx33eRlLLL1HwaAUGo3LKX9Rgc3ZM2QjepXsHE2QOn4BPtOgihtDwMYx07KmdQ45DkRKVE0AwEzj3BoyBs1LZVmZ5M%2B5zUjmabBlEvHrnbQ7TYIcH%2B%2BDIQIK0BmkUdrFT4o%2BmiKo5CsWqd081Ji9s7tBktLc1w30mRrdMf0NP%2BfuHywtgQ%3D%3D&X-Amz-Signature=982356a18f1b00ff938e78ba72eed3177678bb2309b11c8b7b3b40d3b742b1d4&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22" />

- Tree Node를 재귀적으로 탐색해가면서 insert, delete, search, traversal 등의 함수들을 수행

## <Null을 사용한 BST와의 비교>

<img src="https://s3.us-west-2.amazonaws.com/secure.notion-static.com/e8511fcc-a8fa-4515-843e-bb0cffbc7291/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=ASIAT73L2G45EK7A3DUP%2F20200118%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20200118T143427Z&X-Amz-Expires=86400&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEDIaCXVzLXdlc3QtMiJGMEQCIEj%2FsaFcdt8K6qxtzN43A%2BJVsXJAOIn47WBwGniMNTNhAiAfaNrl5ZH3KCb6xgZVPLVOl9s38If2dajbYaYkWIjtpSq9Awi7%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F8BEAAaDDI3NDU2NzE0OTM3MCIM1skBXN4%2B1iTDxdkmKpED%2Fk2S8CKE5o2rPqzq%2FEvSXTwCUaFqghLiiKDLcLEoVHxA611i%2FpcE%2FLJ0Hge4k0midYOX087oZ10YPWxT%2FCs5AohxfPdzoee0oyUx4XzTzrpH8vfpWYZSTjb2ulWZgIf6I9GlD6uxezWWUKqmzq%2BHvNvd%2B48NamuFhcxDNWpIpcPYA1lUSYrMzsJikYZEfZxwWIHwiGQGbbMAB96OX2oRLhFIzgKu5n9a%2FsfsJxE7o0Hft3swyVXuOh0FCTf7in6CB7PTnU0MHB8kDAR8bX%2Bt%2FiaAR8rkDdO5Ucc93aBG0YhRNUbnpucm1TwRsS%2FZynH%2FiUzdJFxV%2FMqJC1mkC8mgJCI0N930bL3PpaJUlbeHFKsjbGx4X6Gnlsk1mhEhm%2BJwT9qvhdvm1otyf4eunSCUgTeS7k3jwAIcolG7GgTQjSbw%2Fjsc0RYBRIWKkP0h4dHu0ViPYwe4Q5jbAOtpW%2FQxr%2F%2BrfYHI2i%2B99qNRlsXvhaFKP0uxVPG%2B6RYQIa2SN5xYmgBP%2F9WjmS2%2BMKafoRsJw5MwoKyL8QU67AF2oDqSsgSMrHwVFJQXuEKAZqtLxxsY39cbO6oiIeFp4TZD6XfcAEZT6VM4SOR0rr4Kwb0eoUV%2F3Sh565PYJ8RFSp7Bh3vQyvESr4xXszVuaQNbRTTwllzLob4MD%2BeRlSfR%2F6NWBJPt6te290PdIs4ZohUbg%2FGxGuYk%2BEBrLGIaLsR6WnlUKsSBZ%2B9pHf8tUzhB9v9Sbk1fSCl%2Bk3d1DJZ66LhUecyPj%2FE7pYwdbp8zRJNedzmeU%2FXs7G6JLunCfkL9Lz7a%2FreyHrlYAt7xPGWrnZSBbqKQveLf5urC481p6f0BsHbeDxeTH%2BWIjw%3D%3D&X-Amz-Signature=e951334ed16107737721de9d64842ecd5fa2b53d8cb18af26befece782212028&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22"/>
