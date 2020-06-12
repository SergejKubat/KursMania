var ime = document.querySelector('#ime');
var dataList = document.querySelector('#preporuke');

ime.addEventListener('keyup', getPreporuke);

function getPreporuke() {
    dataList.innerHTML = '';
    var text = ime.value;
    if (text !== '') {
        var xhr = new XMLHttpRequest();
        var url = 'preporuke?q=' + text;
        xhr.open('GET', url);
        xhr.send();

        xhr.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                var preporuke = xhr.responseText.split('|');
                console.log(preporuke);
                preporuke.forEach(function (preporuka) {
                    var option = document.createElement('option');
                    option.value = preporuka;
                    dataList.appendChild(option);
                });
            }
        }
    }
}

