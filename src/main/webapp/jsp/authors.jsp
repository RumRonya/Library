<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Электронная библиотека: главная</title>
    <link rel="shortcut icon" href="/img/logo_ico.ico" type="image/x-icon">
    <link href="/css/style.css" rel="stylesheet" type="text/css">
    <link href="/css/reset.css" rel="stylesheet" type="text/css">
</head>
<body>
<header>
    <a href="/index">
        <figure id="for_logo">
            <img id="logo_img" src="/img/logo.png" width="100"/>
            <figcaption>
                <h2 id="library">Электронная библиотека</h2>
                <h1 id="name_library">Библчервь</h1>
            </figcaption>
        </figure>
    </a>



    <nav id="main">

        <img id="humb" src="/img/humb.png" onclick="humb_open(this)">

        <ul>
            <li class="main_nav"><a href="/about">О нас</a></li>
            <li class="main_nav"><a href="/books">Книги</a></li>
            <li class="main_nav" id="here"><a href="#">Авторы</a></li>
            <li class="main_nav" id="for_humb"><a href="genres.php">Жанры</a></li>
            <li class="main_nav"><a href="/serias">Серии</a></li>
            <li class="main_nav"><a href="faq.php">FAQ</a></li>
        </ul>

        <form method="post" id="search">
            <input id="search_line" type="search" name="search" size="30"/>
            <input type="submit" name="submit_search" value="Поиск"/>
        </form>

        <p id='for_line'></p>
    </nav>

</header>


<article id="content">

    <nav id="nav_genre">
        <ul>
            <c:forEach items="${genresFromServer}" var="genre">
            <li>
                <a href="/genre?id=${genre.id}">${genre.nameGenre}</a>
            </li>
            </c:forEach>
        </ul>
    </nav>

    <article id="right_content">
        <c:forEach items="${authorsWithBooksFromServer}" var="author">
        <article class="one_text">
            <a  href="/author?id=${author.idAuthor}">
                    ${author.nameAuthor}
                    (${author.countBooks})
            </a>
        </article>
        </c:forEach>

    </article>


</article>

    <footer>
    <section id="developer">
        <p>Дизайн</p>
        <p>Кузьмич Вероника</p>
    </section>

    <section id="vlad">
        <p>Электроная библиотека</p>
        <p>"Библчервь"</p>
    </section>
</footer>
</body>
</html>