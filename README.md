# account-manager



#### How to

1. Clone project from GitHub
2. Build project with Maven
3. Start application by running main method in `AccountManager`
4. Access service in local server `http://localhost:8080`

#### Endpoints

1. Check balance of an account

```java
/v1/account/{accountNum}
```

2. Transfer balance from one account to another

```
/v1/account/transfer
```

Sample request body:

``` json
{

  "fromAccount": 88888888,

  "toAccount": 12345678,

  "amount": 1000

}
```

#### Testing

1. Unit tests are located in` AccountManagerTests`
2. API/Load test was performed with JMeter profile `/src/test/Load Test.jmx`