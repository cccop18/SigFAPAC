// Seleciona os elementos necessários
const navIcon = document.getElementById('nav-icon');
const menuList = document.querySelector('nav ul');

// Define os caminhos das imagens
const abrirIcon = '../IMAGENS/menu-abrir.png';
const fecharIcon = '../IMAGENS/menu-fechar.png';

// Evento de clique para alternar o menu
navIcon.addEventListener('click', function() {
    // Verifica se o menu está ativo ou inativo
    if (menuList.classList.contains('inativo-menu')) {
        // Se estiver inativo, remove a classe e troca a imagem para o ícone de fechar
        menuList.classList.remove('inativo-menu');
        navIcon.src = fecharIcon;
    } else {
        // Se estiver ativo, adiciona a classe inativo de volta e troca a imagem para o ícone de abrir
        menuList.classList.add('inativo-menu');
        navIcon.src = abrirIcon;
    }
});
