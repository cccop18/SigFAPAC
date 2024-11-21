const sidebar = document.getElementById('sidebar');
const toggleBtn = document.getElementById('toggle-btn');
const menuIcon = document.getElementById('menu-icon');
const mainContent = document.getElementById('main-content');

// Função para ajustar a altura da sidebar de acordo com o main
function adjustSidebarHeight() {
    sidebar.style.height = `${400}px`;
    const mainHeight = mainContent.offsetHeight;
    sidebar.style.height = `${mainHeight}px`;
    console.log(mainHeight)
}

toggleBtn.addEventListener('click', () => {
    sidebar.classList.toggle('expanded');
    if (sidebar.classList.contains('expanded')) {
        menuIcon.src = "../IMAGENS/seta-esquerda.png";
    } else {
        menuIcon.src = "../IMAGENS/seta-direita.png";
    }
    setTimeout(adjustSidebarHeight, 500);
});

const menuItems = document.querySelectorAll('.has-submenu');

menuItems.forEach(item => {
    const toggleSubmenu = item.querySelector('.toggle-submenu img');
    item.addEventListener('click', () => {
        item.classList.toggle('expanded');
        if (item.classList.contains('expanded')) {
            toggleSubmenu.src = "../IMAGENS/sinal-de-seta-para-cima-para-navegar.png";
        } else {
            toggleSubmenu.src = "../IMAGENS/sinal-de-seta-para-baixo-para-navegar.png";
        }
    });
});


window.addEventListener('resize', adjustSidebarHeight);
adjustSidebarHeight();

function monitorarLarguraTela() {
    const largura = window.innerWidth;
    console.log("A largura da tela é: " + largura + "px");
    setTimeout(adjustSidebarHeight, 500);
}

window.addEventListener('resize', monitorarLarguraTela);
monitorarLarguraTela();
