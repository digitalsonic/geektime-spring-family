```puml
Customer -> Waiter: 询问菜单
Customer -> Waiter: 点单
Customer -> Waiter: 支付
Waiter --> Barista: 通知开始制作
Barista -> Barista: 制作咖啡
Barista --> Waiter: 通知制作完毕
Waiter --> Customer: 通知取咖啡
```

```puml
component Customer
component Waiter
component Barista
database DB
queue MQ
storage Cache

Customer -> Waiter
Waiter -> Cache
Waiter -> DB
Waiter ..> MQ
MQ ..> Barista
Barista -> DB
Barista ..> MQ
MQ ..> Waiter
MQ ..> Customer
```

```puml
object Customer
object Waiter
object Barista
object Coffee
object Order

Order "1" *-- "1..n" Coffee
Order "n" -- "1" Customer: order
Order "n" -- "1" Waiter: serve
Order "n" -- "0..1" Barista: brew
```

```puml
state 可取消 {
    [*] --> 初始
    初始 --> 已支付
    初始 --> 取消
    已支付 --> 取消
    取消 --> [*]
}
state 不可取消 {
    已支付 --> 制作中
    制作中 --> 制作完毕
    制作完毕 --> 已取货
    已取货 --> [*]
}
```