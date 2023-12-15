INSERT INTO public.tb_pessoa (ds_nome,ds_email,dt_atualizacao,id_dom_situacao,ds_telefone,sg_tipo_pessoa) VALUES
	 ('razão social xpto',NULL,'2023-11-02 23:59:28.419824-03',1,NULL,'PESSOA_JURIDICA');

INSERT INTO public.tb_pessoa (ds_nome,ds_email,dt_atualizacao,id_dom_situacao,ds_telefone,sg_tipo_pessoa) VALUES
	 ('TESTE','TESTE@email.com','2023-11-02 23:59:27.55137-03',1,'7937268048','PESSOA_JURIDICA');

INSERT INTO public.tb_pessoa_fisica (id_pessoa,id_dom_genero,cd_cpf) VALUES
	 (1,700,'74023366030');

INSERT INTO public.tb_pessoa_juridica (id_pessoa,cd_cnpj,ds_nome_fantasia,id_dom_ramo_ativ) VALUES
	 (2,'86285739000102','TESTE',400);

INSERT INTO tb_tenant (cd_tenant,dt_inicio_amostra_gratis,dt_fim_amostra_gratis,dt_atualizacao,dt_cadastro,id_pessoa_juridica,ds_nome,qt_usuario) VALUES
	 ('a7efdd20-e12b-48fe-81fe-0b109db5da95','2023-11-02 23:59:31.528966-03','2023-11-09 23:59:31.529011-03','2023-11-02 23:59:31.532522-03','2023-11-02 23:59:31.53255-03',2,'razão social xpto',1);

INSERT INTO tb_usuario (id_pessoa,dt_atualizacao,id_dom_situacao,id_tenant_selecionado,ds_senha,ds_token) VALUES
	 (1,'2023-11-02 23:59:30.930427-03',1,'a7efdd20-e12b-48fe-81fe-0b109db5da95','$2a$12$lYEi9pbO69z826/k6n1cyerufHdcCKdvcBIsPmxGXy2f0oFyhFenS',NULL);

