ALTER TABLE transacoes ADD COLUMN usuario_id bigint not null;
ALTER TABLE transacoes ADD CONSTRAINT usuario_id 
FOREIGN KEY(usuario_id) REFERENCES usuarios(id);
     