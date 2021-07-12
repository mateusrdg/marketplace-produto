insert into categoria_produto(id,descricao)values(1,'Acessórios de Informática');
insert into categoria_produto(id,descricao)values(2,'Material Escolar');


insert into produto_categorias(produto_id,categoria_id)values(1,1),(5,1),(8,1);
insert into produto_categorias(produto_id,categoria_id)values(2,2),(3,2),(4,2),(6,2);
insert into produto_categorias(produto_id,categoria_id)values(9,2);

insert into venda(id,nome)values(1,'jose');
insert into venda(id,nome)values(2,'antonio');

insert into item(id,preco,produto,quantidade,id_venda)values(1,15,'Teclado',2,1);
insert into item(id,preco,produto,quantidade,id_venda)values(2,1,'Lapis',1,2);
insert into item(id,preco,produto,quantidade,id_venda)values(3,15,'Teclado',2,1);
insert into item(id,preco,produto ,quantidade,id_venda)values(4,1,'Borracha',2,2);
insert into item(id,preco,produto ,quantidade,id_venda)values(5,10,'caderno',1,2);