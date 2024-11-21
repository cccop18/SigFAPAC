/* ========================== CARREGAMENTO DE IMAGEM ========================== */

// Obtém os elementos do input de upload de imagem e da div de visualização
const imageUpload = document.getElementById('imageUpload');
const preview = document.getElementById('preview');

// Função para carregar a imagem padrão
function loadDefaultImage() {
    const defaultImagePath = '../IMAGENS/sem-foto.png';
    const img = document.createElement('img');
    img.src = defaultImagePath;
    
    // Limpa o conteúdo anterior da div e insere a imagem padrão
    preview.innerHTML = '';
    preview.appendChild(img);
}

// Listener para o evento 'change' no input de arquivo
imageUpload.addEventListener('change', function(event) {
    const file = event.target.files[0];
    if (file) {
        const reader = new FileReader();

        reader.onload = function(e) {
            const img = document.createElement('img');
            img.src = e.target.result;

            // Limpa o conteúdo anterior da div e insere a nova imagem
            preview.innerHTML = '';
            preview.appendChild(img);
        }

        reader.readAsDataURL(file);
    } else {
        // Se nenhum arquivo for selecionado, carrega a imagem padrão
        loadDefaultImage();
    }
});

// Carrega a imagem padrão ao iniciar
loadDefaultImage();