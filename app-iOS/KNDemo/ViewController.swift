//
//  ViewController.swift
//  KNDemo
//
//  Created by 周奕成 on 2019/12/16.
//  Copyright © 2019 周奕成. All rights reserved.
//

import UIKit
//引入公共库
import multiplatform_logic

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        //kotlin中的top-level方法，直接编译为对应的文件名d调用
        platformTV.text = LogicKt.getPlatform()
    }

    @IBOutlet weak var platformTV: UILabel!
    
    @IBOutlet weak var inputName: UITextField!
    
    @IBOutlet weak var saveBtn: UIButton!
    
    @IBOutlet weak var loadBtn: UIButton!
    
    @IBOutlet weak var loadName: UILabel!
    
    @IBAction func saveClick(_ sender: UIButton) {
        let name = inputName.text
        //kotlin中的top-level方法，直接编译为对应的文件名d调用
        CommonKt.setUser(username: name!)
    }
    
    
    @IBAction func loadClick(_ sender: UIButton) {
        //kotlin中的top-level方法，直接编译为对应的文件名d调用
        let name = CommonKt.getUser()
        loadName.text = name
    }
    
    
}

