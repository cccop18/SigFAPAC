import { Component, HostListener, Inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-menu-lateral',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink, ReactiveFormsModule],
  templateUrl: './menu-lateral.component.html',
  styleUrls: ['./menu-lateral.component.css']
})
export class MenuLateralComponent {
  isExpanded: boolean = false;
  isMenuOpen: boolean[] = [false, false, false]; // Controla a expansão dos submenus
  menuIcon: string = "assets/img/seta-direita.png";

  constructor(@Inject(PLATFORM_ID) private platformId: object) {}

  toggleSidebar(): void {
    this.isExpanded = !this.isExpanded;
    this.menuIcon = this.isExpanded ? "assets/img/seta-esquerda.png" : "assets/img/seta-direita.png";

    setTimeout(() => this.adjustSidebarHeight(), 500); // Ajusta após expandir o menu
  }

  toggleSubmenu(index: number): void {
    this.isMenuOpen[index] = !this.isMenuOpen[index];
    setTimeout(() => this.adjustSidebarHeight(), 500); // Ajusta a altura após abrir o submenu
  }

  // Ajustar a altura do sidebar conforme a altura do conteúdo principal (mainContent)
  adjustSidebarHeight(): void {
    if (isPlatformBrowser(this.platformId)) {
      const sidebar = document.getElementById('sidebar');
      const mainContent = document.getElementById('main-content');

      if (sidebar && mainContent) {
        // Define a altura do sidebar para ser a mesma do mainContent
        const mainHeight = mainContent.offsetHeight;
        sidebar.style.height = `${mainHeight}px`;
        console.log('Sidebar height adjusted:', mainHeight);
      }
    }
  }

  @HostListener('window:resize', ['$event'])
  onResize(): void {
    setTimeout(() => this.adjustSidebarHeight(), 500);
  }

  ngOnInit(): void {
    // Ajusta a altura no início
    setTimeout(() => this.adjustSidebarHeight(), 500);
  }
}
