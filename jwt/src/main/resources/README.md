json web token：
**JWT包含了三部分：**
Header 头部(标题包含了令牌的元数据，并且包含签名和/或加密算法的类型)
Payload 负载 (包含需要传递的数据，例如用户名)
Signature 签名/签证（验证所需）

**Header**
JWT的头部承载两部分信息：token类型和采用的加密算法。
{ 
  "alg": "HS256",
   "typ": "JWT"
} 
声明类型:这里是jwt
声明加密的算法:通常直接使用 HMAC SHA256

**Payload**
载荷就是存放有效信息的地方。
有效信息包含三个部分
1.标准中注册的声明
2.公共的声明
3.私有的声明

标准中注册的声明 (建议但不强制使用) ：
iss: jwt签发者
sub: 面向的用户(jwt所面向的用户)
aud: 接收jwt的一方
exp: 过期时间戳(jwt的过期时间，这个过期时间必须要大于签发时间)
nbf: 定义在什么时间之前，该jwt都是不可用的.
iat: jwt的签发时间
jti: jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击。

公共的声明 ：
公共的声明可以添加任何的信息，一般添加用户的相关信息或其他业务需要的必要信息.但不建议添加敏感信息，
因为该部分在客户端可解密.

私有的声明 ：
私有声明是提供者和消费者所共同定义的声明，一般不建议存放敏感信息，因为base64是对称解密的，
意味着该部分信息可以归类为明文信息。

**Signature**
jwt的第三部分是一个签证信息，这个签证信息由三部分组成：
header (base64后的)
payload (base64后的)
secret
这个部分需要base64加密后的header和base64加密后的payload使用.连接组成的字符串，然后通过header中声明的加密方式
进行加盐secret组合加密，然后就构成了jwt的第三部分。
密钥secret是保存在服务端的，服务端会根据这个密钥进行生成token和进行验证，所以需要保护好。

**JWT的最终结构**
JWT是由三段信息构成的，将这三段信息文本用.连接一起就构成了JWT字符串。
就像这样:
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9
.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ

生成规则：
_base64(Header).base64(Payload).sha256(base64(Header).base64(Payload),secret)_ 
其中sha256是header中写好的
base64是规定的jwt加密方式，所以header和payload不能存放敏感信息
