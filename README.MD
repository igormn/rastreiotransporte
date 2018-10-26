INFORMAÇÕES IMPORTANTES

A partir da sua descrição no e-mail, pensei num sistema de rastreio bem simples. Onde para eu rastrear alguma coisa, eu preciso de uma compra.
A Compra tem um código, cliente e valor. O Cliente tem cpf, nome, e-mail e endereço. O Endereço tem logradouro, número, bairro, cidade, estado
e CEP. O Rastreio tem um código (criado sequencialmente pelo banco de dados), transportadora, data e descrição. A Transportador tem cnpj e nome.

Utilizei o Spring Boot (versão 1.5.16) com Starter para Web (contém o Spring MVC e RestFull), Data JPA (contém JPA e Hibernate), Thymeleaf (para
utilização das páginas HTML), Devtools (para HotDeploy da aplicação) e Banco de Dados PostgreSQL (versão 42.2.5 para a aplicação e versão 11.0
para a minha máquina).

Não me preocupei com os CRUDs das Entidades e nem com validações dos campos obrigatórios e/ou suas formatações.
A descrição dos métodos já diz bem o que ele faz. Isso é uma boa prática.

Fiz os endpoints do serviço para Consultar os Rastreios e Cadastrar um Rastreio (vide classe TrackingRestController).

Você consegue consultar ou cadastrar rastreio tanto por um Cliente Rest do próprio Browser (chamando o endpoint do Serviço) quanto pela aplicação.
<=> Chamada pelo Browser (utilizei o Restlet Client do Chrome)
	* Consultar Rastreios
	  - URL: http://localhost:8080/flashcourier/transport/trackings/FC20181025P0001
	  - Método: GET
	  - Headers: Accept (application/json)
	* Cadastrar Rastreio
	  - URL: http://localhost:8080/flashcourier/transport/trackings
	  - Método: POST
	  - Headers: Accept (application/json) e Content-Type (application/json)
	  - Body: {
				"codePurchase": "FC20181025P0001",
  				"cnpjShippingCompany": "05628064000155",
				"date": "26/10/2018 14:00:00",
				"description": "Transportadora entregou o Produto!"
			  }
	* Fluxo: TrackingRestController (recebe um TrackingDTO) => TrackingService (valida as regras de negócio e transforma o TrackingDTO em um Tracking) => TrackingDAO
<=> Chamada pela Aplicação
	* Acessar a seguinte URL: http://localhost:8080/flashcourier/
	* Você conseguirá consultar e também cadastrar um rastreio.
	* Fluxo: TrackingController => TrackingRestController (recebe um TrackingDTO) => TrackingService (valida as regras de negócio e transforma o TrackingDTO em um 
	Tracking) => TrackingDAO

<=> Carga Inicial no Banco de Dados
    * Tabela de Endereço
      INSERT INTO public.address(city, neighborhood, "number", state, street, zip_code)
	  VALUES ('São Paulo', 'Vila Prudente', '92', 'SP', 'Rua Orlando Calixto', '03236000');
	* Tabela de Cliente
	  INSERT INTO public.client(cpf, email, name, address_id)
	  VALUES ('01044759518', 'igormn84@yahoo.com', 'Igor Macedo Nascimento', 1);
	* Tabela de Transportadora
	  INSERT INTO public.shipping_company(cnpj, name)
	  VALUES ('05628064000155', 'Flash Courier');
	* Tabela de Compra
	  INSERT INTO public.purchase(code, client_cpf, value)
	  VALUES ('FC20181025P0001', '01044759518', '750');
