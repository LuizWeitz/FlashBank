# FlashBank
Um api de um banco rápido(flash), já que nessa vida, tempo é dinheiro

Funcionalidades dessa API

-Criar conta(automáticamente cria cartão e uma carteira)
<br>
-Transferir dinheiro entre as contas
<br>
-Desbloquear cartão, pois quando cria uma conta, o cartão é gerado e ele vem bloqueado
<br>
-Pagamento à vista(retira automáticamente o valor da conta de quem está pagando e add na conta de quem está vendendo)
<br>
-Pagamento parcelado, ele gera automáticamente as parcelas com data de vencimento de 1-1 mês e com o valor dividido
<br>
-Atribuição das parcelas a conta 
<br>
-Criar novas carteira para mesma conta
<br>
-Criar novos cartãos para a mesma conta
...

Algumas das principais funções dessa API
<br>

--- SEGURANÇA --- 
<br>

Fiz questão de add um alto nível de segurança, logo de se tratar de um banco
<br>

-JWT com geração de token por sessão
<br>
-Validação de senha da conta
<br>
-validação de senha de cartão na hora de pagar
<br>

--- TECNOLOGIAS ---
<br>

-Spring Boot
<br>
-Postgresql
<br>
-JPA
<br>
-JWT
<br>
-Spring Security
<br>
-Hibernate
<br>
-SQL
<br>
...
<br>

Feito com ❤️ BY LUIZ WEITZ
<br>

