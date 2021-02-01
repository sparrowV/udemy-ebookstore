insert into USER(id,first_name,last_name,email)
values( USER_SEQ.nextval ,'tom','hitchens','tom@gmail.com'),
( USER_SEQ.nextval ,'kate','smith','kate@gmail.com'),
( USER_SEQ.nextval ,'Jane','richards','jane@gmail.com'),
( USER_SEQ.nextval ,'Alan','Pitt','alan@gmail.com');

insert into EBOOK(id,authors,title)
values( EBOOK_SEQ.nextval ,'Napoleon Hill','Think and Grow Rich'),
( EBOOK_SEQ.nextval ,'Plato','Republic');
