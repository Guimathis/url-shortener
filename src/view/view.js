document.addEventListener("DOMContentLoaded", function () {
    document.getElementById('urlForm').addEventListener('submit', async function (e) {
        e.preventDefault(); // Evita o comportamento padrão de recarregar a página
        await encurtarUrl(); // Chama a função de encurtar URL
    });
});

async function encurtarUrl() {
    const originalUrl = document.getElementById('originalUrl').value;

    try {
        const response = await fetch('http://localhost:8080/shorten-url', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ url: originalUrl })
        });

        if (response.ok) {
            const data = await response.json();

            // Cria o elemento de link
            const shortUrlElement = document.createElement('a');
            shortUrlElement.href = data.url; // URL encurtada recebida do backend
            shortUrlElement.target = "_blank"; // Abre em uma nova aba
            shortUrlElement.innerText = data.url; // Texto do link

            // Exibe o link na página
            const shortUrlContainer = document.getElementById('shortUrl');
            shortUrlContainer.innerHTML = 'URL encurtada: '; // Limpa o conteúdo anterior
            shortUrlContainer.appendChild(shortUrlElement);
        } else {
            document.getElementById('shortUrl').innerText = 'Erro ao encurtar a URL';
        }
    } catch (error) {
        document.getElementById('shortUrl').innerText = 'Erro ao tentar conectar com o servidor';
        console.error("Erro na requisição:", error);
    }
}
