1. login by POST http://localhost:8080/user-service/user/login
userId=user1&userPassword=password1
and got the token: 
7601460a-40fe-40f3-9f05-95b53f7dbbc5

2.get list of items: GET http://localhost:8080/item-service/item/getAll

{
"itemId": "I6S",
"itemName": "Iphone 6s",
"price": 400
},
  {
"itemId": "I7",
"itemName": "Iphone 7",
"price": 500
},
  {
"itemId": "N5",
"itemName": "Samsung galaxy note 5",
"price": 450
}

3. add items to cart:
POST http://localhost:8080/cart-service/cart
tokenId=4b686bbc-8461-43c8-9fb1-b53180dc3f9a&itemId=I7&amount=2

4. create order
POST http://localhost:8080/cart-service/cart/order
tokenId=4b686bbc-8461-43c8-9fb1-b53180dc3f9a