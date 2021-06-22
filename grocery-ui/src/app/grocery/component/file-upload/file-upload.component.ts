import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {FileUploader} from "ng2-file-upload";
import {GroceryReportService} from "../../service/grocery-report.service";
import {FileItem} from "ng2-file-upload/file-upload/file-item.class";
import {NgxSpinnerService} from "ngx-spinner";
import {ParsedResponseHeaders} from "ng2-file-upload/file-upload/file-uploader.class";

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.css']
})
export class FileUploadComponent implements OnInit {

  uploader!: FileUploader;

  @ViewChild('inputFile')
  inputFile!: ElementRef;

  constructor(private groceryReportService: GroceryReportService,
              private spinnerService: NgxSpinnerService) { }

  ngOnInit() {
    this.uploader = new FileUploader({
      url: this.groceryReportService.FILE_UPLOAD_URL,
      queueLimit: 1,
      method: 'post'
    });

    this.uploader.onProgressItem = (fileItem: FileItem, progress: any) => {
      this.spinnerService.show();
    };

    this.uploader.onAfterAddingFile = (file) => { file.withCredentials = false; };

    this.uploader.onCompleteItem = (item: any, response: any, status: any, headers: any) => {
      this.spinnerService.hide();
    };

    this.uploader.onSuccessItem = (item: FileItem, response: string, status: number, headers: ParsedResponseHeaders) => {
      console.log('File uploaded successfully.', item, status, response);
      this.inputFile.nativeElement.value = '';
      alert('File uploaded successfully');
    }

    this.uploader.onErrorItem = (item: FileItem, response: string, status: number, headers: ParsedResponseHeaders) => {
      console.log('Failed to upload.', item, status, response);
      alert('Error in uploading file.');
    }
  }
}
