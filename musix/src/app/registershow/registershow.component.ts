import { Component, OnInit } from '@angular/core';

import { RegisterService } from '../service/register.service';
import { Register } from '../register';

@Component({
  selector: 'app-registershow',
  templateUrl: './registershow.component.html',
  styleUrls: ['./registershow.component.css']
})
export class RegistershowComponent implements OnInit {

  register:Register=new Register();
  //create array to store
  registers:Array<Register>=[];
  
  constructor(private dataService:RegisterService) { }

  ngOnInit() {
    console.log("register show component");
    this.dataService.getdata().subscribe(data=>{
      console.log(data);
      this.registers=data;      
    });
    this.register=new Register();

}
}
