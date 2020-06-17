<!DOCTYPE html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Greska 500</title>
    <link href="https://fonts.googleapis.com/css?family=Montserrat:200,400,700" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="resources/css/error.css" />
</head>
<body>

    <div id="notfound">
        <div class="notfound">
            <div class="notfound-404">
                <h1>Ups!</h1>
                <h2>Doslo je do greske na serveru!</h2>
            </div>
            <button id="nazad">Nazad</button>
        </div>
    </div>

    <script>
        var nazad = document.querySelector('#nazad');
        nazad.addEventListener('click', function () {
            window.history.back();
        })
    </script>
</body>

</html>