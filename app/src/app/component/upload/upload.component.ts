import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpEventType } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-upload',
  imports: [FormsModule, CommonModule, RouterLink, ReactiveFormsModule],
  standalone: true,
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent implements OnInit {
  selectedFile: File | null = null;
  fileList: string[] = [];

  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.loadFileList();
  }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }

  onSubmit() {
    if (this.selectedFile) {
      const uploadData = new FormData();
      uploadData.append('file', this.selectedFile, this.selectedFile.name);
  
      this.http.post('http://localhost:9000/fapacapi/files/upload', uploadData, {
        reportProgress: true,
        observe: 'events',
        responseType: 'text' // Tratar a resposta como texto, não JSON
      })
      .subscribe(event => {
        if (event.type === HttpEventType.Response) {
          console.log('Upload completed: ', event.body); // A resposta agora será a string retornada
          this.loadFileList(); // Recarrega a lista após upload
        }
      }, error => {
        console.error('Upload failed: ', error);
      });
    }
  }
  

  loadFileList() {
    this.http.get<string[]>('http://localhost:9000/fapacapi/files/list')
      .subscribe(response => {
        this.fileList = response;
      });
  }
}
