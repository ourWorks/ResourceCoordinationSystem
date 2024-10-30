  骤1：初始化变量
root_dir = os.getcwd()
my_app_ignore_path = os.path.join(root_dir, 'my-app', '.gitignore')
my_app_ui_ignore_path = os.path.join(root_dir, 'my-app-ui', '.gitignore')
root_ignore_path = os.path.join(root_dir, '.gitignore')

# 步骤2：读取'my-app'下的.gitignore文件内容，并添加子目录前缀
with open(my_app_ignore_path, 'r') as file:
    my_app_ignores = set(f"my-app/{line}" for line in file.read().splitlines() if line and not line.startswith("#"))

# 步骤3：读取'my-app-ui'下的.gitignore文件内容，并添加子目录前缀
with open(my_app_ui_ignore_path, 'r') as file:
    my_app_ui_ignores = set(f"my-app-ui/{line}" for line in file.read().splitlines() if line and not line.startswith("#"))

# 步骤4：合并需要忽略内容的唯一模式
combined_ignores = my_app_ignores.union(my_app_ui_ignores)

# 步骤5：将合并后的模式写入根目录的.gitignore文件
with open(root_ignore_path, 'w') as file:
    for pattern in sorted(combined_ignores):
        file.write(pattern + '\n')

# 步骤6：通知用户
print("已将'my-app'和'my-app-ui'子目录下的.gitignore文件中的忽略内容（包含子文件夹名称）合并到根目录的.gitignore文件中。")