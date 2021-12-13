# FlashBank
Um api de um banco rápido(flash), já que nessa vida, tempo é dinheiro

Funcionalidades dessa API

-Criar conta(automáticamente cria cartão e uma carteira)
-Transferir dinheiro entre as contas
-Desbloquear cartão, pois quando cria uma conta, o cartão é gerado e ele vem bloqueado
-Pagamento à vista(retira automáticamente o valor da conta de quem está pagando e add na conta de quem está vendendo)
-Pagamento parcelado, ele gera automáticamente as parcelas com data de vencimento de 1-1 mês e com o valor dividido
-Atribuição das parcelas a conta 
-Criar novas carteira para mesma conta
-Criar novos cartãos para a mesma conta
...

Algumas das principais funções dessa API

--- SEGURANÇA --- 

Fiz questão de add um alto nível de segurança, logo de se tratar de um banco

-JWT com geração de token por sessão
-Validação de senha da conta
-validação de senha de cartão na hora de pagar

--- TECLONOGIAS ---

-Spring Boot
-Postgresql
-JPA
-JWT
-Spring Security
-Hibernate
-SQL
...

Feito com ❤️ BY LUIZ WEITZ

