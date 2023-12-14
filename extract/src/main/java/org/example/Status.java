package org.example;

public enum Status {
    PE,CE,FE,PL,CL,FL,PT,CT,FT,PA,CA,FA,PLDM,CLDM,FLDM
}
//        1.Extract (Trích xuất)
//          In Progress - Extracting: Quá trình trích xuất đang diễn ra.
//          Completed - Extract: Quá trình trích xuất đã hoàn thành thành công.
//          Failed - Extract: Quá trình trích xuất thất bại.
//        2.Load (Nạp):
//          In Progress - Loading: Dữ liệu đang được nạp.
//          Completed - Load: Quá trình nạp dữ liệu đã hoàn thành thành công.
//          Failed - Load: Quá trình nạp dữ liệu thất bại.
//        3.Transform (Biến đổi):
//          In Progress - Transforming: Quá trình biến đổi đang diễn ra.
//          Completed - Transform: Quá trình biến đổi đã hoàn thành thành công.
//          Failed - Transform: Quá trình biến đổi thất bại.
//        4.Aggregate (Tổng hợp):
//          In Progress - Aggregating: Quá trình tổng hợp đang diễn ra.
//          Completed - Aggregate: Quá trình tổng hợp đã hoàn thành thành công.
//          Failed - Aggregate: Quá trình tổng hợp thất bại.
//        5.Load into Data Mart (Nạp vào Data Mart):
//          In Progress - Loading into Data Mart: Dữ liệu đang được nạp vào Data Mart.
//          Completed - Load Data Mart: Quá trình nạp vào Data Mart đã hoàn thành thành công.
//          Failed - Load Data Mart: Quá trình nạp vào Data Mart thất bại.
