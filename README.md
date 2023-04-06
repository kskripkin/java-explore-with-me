# java-explore-with-me
https://github.com/kskripkin/java-explore-with-me/pull/4

Приложение Афиша.

Предагает функционал созданиея событий и сбора участников для участия в нем.

В данном приложении все методы разделены на 3 части:
1) Публичная - предоставляет функционал поиска и фильтрации событий, категорий событий, подборок событий и позволяет видеть комментарии пользователей.
2) Закрытая - позволяет пользователям добавлять новые события, редактировать и просматривать их, подтверждать или отклонять заявки на участие.
3) Административная - предоставляет шировкие возможности по поиску, редактированию и удалению событий, категорий, заявок, пользователей. Позволяет закреплять подборки событий на главной странице.

Проект состоит из модулей и подмодулей:
ewm-service - основной сервис.
    http-client - реализует отправку статистики в подмодуль stats-server.
    service - основное приложение, в котором реализован весь основной функционал. Хранит данные в отдельной PostgresDB.
stats - сервис статистики.
    stat-dto - модели для использования в модулях service, stats-server.
    stats-server - сервис статистики с сохранением данных в PostgresDB.
