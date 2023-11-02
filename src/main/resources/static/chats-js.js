document.addEventListener("DOMContentLoaded", function () {
    const chatList = document.getElementById("chatList");
    const loadChatsButton = document.getElementById("loadChatsButton");

    // Функция для загрузки слов при нажатии кнопки
    loadChatsButton.addEventListener("click", function () {
        // Здесь можно сделать AJAX-запрос к вашему серверу, чтобы получить список слов
        // и добавить их в список на странице
        // Пример запроса с использованием fetch:

        fetch(`/chat/all`)
            .then(response => response.json())
            .then(data => {
                // Очистить текущий список слов
                chatList.innerHTML = "";

                // Добавить полученные слова в список
                data.forEach(chat => {
                    const li = document.createElement("li");
                    li.textContent = chat;
                    chatList.appendChild(li);
                });
            })
            .catch(error => {
                console.error("Ошибка при загрузке слов: " + error);
            });
    });
});