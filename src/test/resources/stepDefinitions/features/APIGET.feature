Scenario Outline: GET pet request using pet id

Given "GET petid" api pet request endpoint is set as "pet/petid"
Then API should return status 200
Then the response will return <name>
Examples: Pets
| name |
|himars |