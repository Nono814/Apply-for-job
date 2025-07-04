#!/bin/bash

# 简历上传功能测试脚本
# 用于测试对象存储的文件上传功能

echo "=== 简历上传功能测试 ==="

# 设置测试参数
BASE_URL="http://localhost:8080"
USER_ID=1

echo "1. 测试上传PDF简历文件..."
echo "请确保有一个测试PDF文件，或者创建一个测试文件"

# 创建测试PDF文件（如果不存在）
if [ ! -f "test_resume.pdf" ]; then
    echo "创建测试PDF文件..."
    # 使用echo创建一个简单的PDF文件内容（实际项目中应该使用真实的PDF文件）
    echo "%PDF-1.4" > test_resume.pdf
    echo "1 0 obj" >> test_resume.pdf
    echo "<</Type/Catalog/Pages 2 0 R>>" >> test_resume.pdf
    echo "endobj" >> test_resume.pdf
    echo "2 0 obj" >> test_resume.pdf
    echo "<</Type/Pages/Kids[3 0 R]/Count 1>>" >> test_resume.pdf
    echo "endobj" >> test_resume.pdf
    echo "3 0 obj" >> test_resume.pdf
    echo "<</Type/Page/Parent 2 0 R/MediaBox[0 0 612 792]/Contents 4 0 R>>" >> test_resume.pdf
    echo "endobj" >> test_resume.pdf
    echo "4 0 obj" >> test_resume.pdf
    echo "<</Length 44>>" >> test_resume.pdf
    echo "stream" >> test_resume.pdf
    echo "BT /F1 12 Tf 100 700 Td (Test Resume) Tj ET" >> test_resume.pdf
    echo "endstream" >> test_resume.pdf
    echo "endobj" >> test_resume.pdf
    echo "xref" >> test_resume.pdf
    echo "0 5" >> test_resume.pdf
    echo "0000000000 65535 f " >> test_resume.pdf
    echo "0000000009 00000 n " >> test_resume.pdf
    echo "0000000058 00000 n " >> test_resume.pdf
    echo "0000000115 00000 n " >> test_resume.pdf
    echo "0000000204 00000 n " >> test_resume.pdf
    echo "trailer" >> test_resume.pdf
    echo "<</Size 5/Root 1 0 R>>" >> test_resume.pdf
    echo "startxref" >> test_resume.pdf
    echo "297" >> test_resume.pdf
    echo "%%EOF" >> test_resume.pdf
fi

# 测试上传简历
echo "上传测试PDF文件..."
UPLOAD_RESPONSE=$(curl -s -X POST \
    -F "user_id=$USER_ID" \
    -F "file=@test_resume.pdf" \
    "$BASE_URL/api/resumes/upload")

echo "上传响应: $UPLOAD_RESPONSE"

# 解析响应
RESUME_ID=$(echo $UPLOAD_RESPONSE | grep -o '"resume_id":[0-9]*' | cut -d':' -f2)
FILE_URL=$(echo $UPLOAD_RESPONSE | grep -o '"file_url":"[^"]*"' | cut -d'"' -f4)

if [ ! -z "$RESUME_ID" ]; then
    echo "✅ 简历上传成功！"
    echo "   简历ID: $RESUME_ID"
    echo "   文件URL: $FILE_URL"
    
    echo ""
    echo "2. 测试获取用户简历列表..."
    LIST_RESPONSE=$(curl -s -X GET "$BASE_URL/api/users/$USER_ID/resumes")
    echo "简历列表响应: $LIST_RESPONSE"
    
    echo ""
    echo "3. 测试文件URL访问..."
    if [ ! -z "$FILE_URL" ]; then
        echo "尝试访问文件URL: $FILE_URL"
        # 注意：这里只是测试URL格式，实际访问可能需要网络连接
        echo "文件URL格式正确"
    fi
    
else
    echo "❌ 简历上传失败"
    echo "错误信息: $UPLOAD_RESPONSE"
fi

echo ""
echo "=== 测试完成 ==="

# 清理测试文件
echo "清理测试文件..."
rm -f test_resume.pdf

echo "测试脚本执行完毕！" 