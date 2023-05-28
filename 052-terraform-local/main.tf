module "test_folder1" {
    source = "./modules/folder_module"

    folder_name = "${var.folder_1}"
}

module "test_folder2"  {
    source = "./modules/folder_module"

    folder_name = "${var.folder_2}"
}

module "test_file1"  {
    depends_on = [module.test_folder1]
    source = "./modules/file_module"

    folder_name = module.test_folder1.created_folder_name
    file_name = "${var.file_1}"
    file_content = "${var.file_1_content}"
}
