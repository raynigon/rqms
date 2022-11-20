import { Component, EventEmitter, Inject, Input, OnInit, Output } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { LabelFilter } from '../model/label-filter';

@Component({
  selector: 'rqms-label-filter-editor',
  templateUrl: './label-filter-editor.component.html',
  styleUrls: ['./label-filter-editor.component.scss']
})
export class LabelFilterEditorComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<LabelFilterEditorComponent>,
    @Inject(MAT_DIALOG_DATA) public filter: LabelFilter,
  ) { }

  ngOnInit(): void {
  }

  save(): void {
    this.dialogRef.close(this.filter);
  }

}
