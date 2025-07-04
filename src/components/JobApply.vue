<template>
  <div class="job-apply-bg">
    <div class="job-apply-center">
      <div class="job-apply-card">
        <div class="job-apply-header">
          <div class="company-logo-box">
            <!-- <img class="company-logo-img" src="/logo.png" alt="logo" /> -->
          </div>
          <div class="company-name">Company Name</div>
        </div>
        <div class="job-title">Product Manager | 产品经理</div>
        <div class="job-tags-figma">
          <div class="tag-figma">
            <span class="tag-svg-icon" aria-hidden="true">
              <svg width="16" height="16" viewBox="0 0 16 16" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path d="M12.667 1.3335H3.33366C2.96547 1.3335 2.66699 1.63197 2.66699 2.00016V14.0002C2.66699 14.3684 2.96547 14.6668 3.33366 14.6668H12.667C13.0352 14.6668 13.3337 14.3684 13.3337 14.0002V2.00016C13.3337 1.63197 13.0352 1.3335 12.667 1.3335Z" stroke="#6B42DD" stroke-opacity="0.5" stroke-linecap="round" stroke-linejoin="round"/>
                <path d="M5.33301 1.3335H8.33301V6.66683L6.83301 5.3335L5.33301 6.66683V1.3335Z" stroke="#6B42DD" stroke-opacity="0.5" stroke-linecap="round" stroke-linejoin="round"/>
                <path d="M5.33301 9.3335H8.66634" stroke="#6B42DD" stroke-opacity="0.5" stroke-linecap="round"/>
                <path d="M5.33301 11.3335H10.6663" stroke="#6B42DD" stroke-opacity="0.5" stroke-linecap="round"/>
              </svg>
            </span>
            全职
          </div>
          <div class="tag-figma">
            <span class="tag-svg-icon" aria-hidden="true">
              <svg width="16" height="16" viewBox="0 0 16 16" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path d="M8 14.6668C8 14.6668 13 10.6668 13 6.3335C13 3.57206 10.7614 1.3335 8 1.3335C5.23857 1.3335 3 3.57206 3 6.3335C3 10.6668 8 14.6668 8 14.6668Z" stroke="#6B42DD" stroke-opacity="0.5" stroke-linejoin="round"/>
                <path d="M8 8.3335C9.10457 8.3335 10 7.43806 10 6.3335C10 5.22893 9.10457 4.3335 8 4.3335C6.89543 4.3335 6 5.22893 6 6.3335C6 7.43806 6.89543 8.3335 8 8.3335Z" stroke="#6B42DD" stroke-opacity="0.5" stroke-linejoin="round"/>
              </svg>
            </span>
            远程
          </div>
          <div class="tag-figma">
            <span class="tag-svg-icon" aria-hidden="true">
              <svg width="20" height="20" viewBox="6 8 16 12" fill="none" xmlns="http://www.w3.org/2000/svg">
                <rect x="6" y="8" width="16" height="12" rx="3" fill="#EEEEFF"/>
                <path d="M9.3335 11.3335H22.6668V19.3335H9.3335V11.3335Z" stroke="#6B42DD" stroke-opacity="0.5" stroke-linejoin="round"/>
                <path fill-rule="evenodd" clip-rule="evenodd" d="M9.3335 14.0002C10.8063 14.0002 12.0002 12.8063 12.0002 11.3335H9.3335V14.0002Z" stroke="#6B42DD" stroke-opacity="0.5" stroke-linecap="round" stroke-linejoin="round"/>
                <path fill-rule="evenodd" clip-rule="evenodd" d="M9.3335 16.6665C10.8063 16.6665 12.0002 17.8604 12.0002 19.3332H9.3335V16.6665Z" stroke="#6B42DD" stroke-opacity="0.5" stroke-linecap="round" stroke-linejoin="round"/>
                <path fill-rule="evenodd" clip-rule="evenodd" d="M22.6667 16.6665V19.3332H20C20 17.8604 21.1939 16.6665 22.6667 16.6665Z" stroke="#6B42DD" stroke-opacity="0.5" stroke-linecap="round" stroke-linejoin="round"/>
                <path fill-rule="evenodd" clip-rule="evenodd" d="M22.6667 14.0002C21.1939 14.0002 20 12.8063 20 11.3335H22.6667V14.0002Z" stroke="#6B42DD" stroke-opacity="0.5" stroke-linecap="round" stroke-linejoin="round"/>
                <path d="M16.0002 17.3335C16.9206 17.3335 17.6668 16.4381 17.6668 15.3335C17.6668 14.2289 16.9206 13.3335 16.0002 13.3335C15.0797 13.3335 14.3335 14.2289 14.3335 15.3335C14.3335 16.4381 15.0797 17.3335 16.0002 17.3335Z" stroke="#6B42DD" stroke-opacity="0.5" stroke-linejoin="round"/>
              </svg>
            </span>
            $2,000~$3,000/年
          </div>
        </div>
        <form class="job-form-figma" @submit.prevent="nextStep">
          <div class="form-group">
            <label for="name">姓名<span class="required">*</span></label>
            <input id="name" v-model="form.name" type="text"
              :class="[{'input-error': errors.name, 'input-focus': focused.name}]"
              @focus="onFocus('name')" @blur="onBlur('name')" autocomplete="off" />
            <div v-if="errors.name" class="input-error-tip">
              <span class="input-error-icon" aria-hidden="true">
                <svg width="16" height="16" viewBox="0 0 16 16" fill="none" xmlns="http://www.w3.org/2000/svg">
                  <circle cx="8" cy="8" r="7.5" stroke="#FF4848"/>
                  <rect x="7.25" y="4" width="1.5" height="5.5" rx="0.75" fill="#FF4848"/>
                  <rect x="7.25" y="11" width="1.5" height="1.5" rx="0.75" fill="#FF4848"/>
                </svg>
              </span>
              {{ errors.name }}
            </div>
          </div>
          <div class="form-group">
            <label for="email">邮箱<span class="required">*</span></label>
            <input id="email" v-model="form.email" type="email"
              :class="[{'input-error': errors.email, 'input-focus': focused.email}]"
              @focus="onFocus('email')" @blur="onBlur('email')" autocomplete="off" />
            <div v-if="errors.email" class="input-error-tip">
              <span class="input-error-icon" aria-hidden="true">
                <svg width="16" height="16" viewBox="0 0 16 16" fill="none" xmlns="http://www.w3.org/2000/svg">
                  <circle cx="8" cy="8" r="7.5" stroke="#FF4848"/>
                  <rect x="7.25" y="4" width="1.5" height="5.5" rx="0.75" fill="#FF4848"/>
                  <rect x="7.25" y="11" width="1.5" height="1.5" rx="0.75" fill="#FF4848"/>
                </svg>
              </span>
              {{ errors.email }}
            </div>
          </div>
          <div class="form-group">
            <label for="phone">手机号<span class="required">*</span></label>
            <div class="input-prefix-wrapper">
              <input id="phone" v-model="form.phone" type="tel"
                :class="[{'input-error': errors.phone, 'input-focus': focused.phone}]"
                @focus="onFocus('phone')" @blur="onBlur('phone')" autocomplete="off" />
              <span class="input-prefix">+86</span>
            </div>
            <div v-if="errors.phone" class="input-error-tip">
              <span class="input-error-icon" aria-hidden="true">
                <svg width="16" height="16" viewBox="0 0 16 16" fill="none" xmlns="http://www.w3.org/2000/svg">
                  <circle cx="8" cy="8" r="7.5" stroke="#FF4848"/>
                  <rect x="7.25" y="4" width="1.5" height="5.5" rx="0.75" fill="#FF4848"/>
                  <rect x="7.25" y="11" width="1.5" height="1.5" rx="0.75" fill="#FF4848"/>
                </svg>
              </span>
              {{ errors.phone }}
            </div>
          </div>
          <div class="form-group">
            <label>简历<span class="required">*</span></label>
            <div class="resume-upload-box" @click="triggerFileInput">
              <input ref="fileInput" type="file" accept=".pdf,.docx" @change="handleFileChange" style="display:none" />
              <div class="resume-upload-content">
                <template v-if="!fileName">
                  <span class="upload-svg-icon" aria-hidden="true">
                    <svg width="16" height="16" viewBox="0 0 16 16" fill="none" xmlns="http://www.w3.org/2000/svg">
                      <g clip-path="url(#clip0_6380_1885)">
                        <path d="M8 14.6668C11.682 14.6668 14.667 11.6821 14.667 8.00016C14.667 4.31826 11.682 1.3335 8 1.3335C4.31804 1.3335 1.33325 4.31826 1.33325 8.00016C1.33325 11.6821 4.31804 14.6668 8 14.6668Z" stroke="#6B42DD" stroke-linejoin="round"/>
                        <path d="M8 5.3335V10.6668" stroke="#6B42DD" stroke-linecap="round" stroke-linejoin="round"/>
                        <path d="M5.33325 8H10.6666" stroke="#6B42DD" stroke-linecap="round" stroke-linejoin="round"/>
                      </g>
                      <defs>
                        <clipPath id="clip0_6380_1885">
                          <rect width="16" height="16" fill="white"/>
                        </clipPath>
                      </defs>
                    </svg>
                  </span>
                  <span class="upload-text">拖拽或选择文件</span>
                </template>
                <template v-else>
                  <span class="upload-filename-bold">{{ fileName }}</span>
                  <button type="button" class="upload-change-btn" @click.stop="triggerFileInput">更改</button>
                </template>
              </div>
              <div class="upload-tip">支持的文件类型：PDF、DOCX，文件大小不超过3M</div>
            </div>
          </div>
          <div class="form-btn-row">
            <button class="figma-btn" type="submit" :disabled="loading">{{ loading ? '提交中...' : '下一步' }}</button>
          </div>
        </form>
        <div v-if="errorMsg" style="color:#FF4D4F;margin-bottom:8px;">{{ errorMsg }}</div>
      </div>
    </div>
    <div class="job-apply-steps-bar-figma">
      <div class="steps-bar-inner-figma">
        <div v-for="(step, idx) in steps" :key="step.title" class="step-bar-block">
          <div class="step-title-figma">{{ step.title }}</div>
          <div
            class="step-bar-progress-block"
            :class="{ active: idx === activeStep.value, future: idx > activeStep.value }"
          ></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { apiFetch, uploadResume } from '../api.js'

const form = reactive({
  name: '',
  email: '',
  phone: '',
  resume: null
})
const fileName = ref('')
const fileInput = ref(null)
const loading = ref(false)
const errorMsg = ref('')

// 业务数据
const userId = ref(null)
const resumeId = ref(null)
const jobId = ref(1) // 如有动态jobId可替换

// 校验状态
const errors = reactive({
  name: '',
  email: '',
  phone: ''
})
const focused = reactive({
  name: false,
  email: false,
  phone: false
})

function triggerFileInput() {
  fileInput.value && fileInput.value.click()
}

function handleFileChange(e) {
  const file = e.target.files[0]
  if (!file) return
  if (file.size > 3 * 1024 * 1024) {
    alert('文件大小不能超过3M')
    fileName.value = ''
    form.resume = null
    return
  }
  fileName.value = file.name
  form.resume = file
}

function validateName() {
  if (!form.name.trim()) {
    errors.name = '请输入姓名';
    return false;
  }
  errors.name = '';
  return true;
}
function validateEmail() {
  if (!form.email.trim()) {
    errors.email = '请输入邮箱';
    return false;
  }
  // 简单邮箱正则
  const emailReg = /^[^@\s]+@[^@\s]+\.[^@\s]+$/;
  if (!emailReg.test(form.email)) {
    errors.email = '请输入正确的邮箱格式';
    return false;
  }
  errors.email = '';
  return true;
}
function validatePhone() {
  if (!form.phone.trim()) {
    errors.phone = '请输入手机号';
    return false;
  }
  // 中国大陆手机号正则
  const phoneReg = /^1[3-9]\d{9}$/;
  if (!phoneReg.test(form.phone)) {
    errors.phone = '请输入正确的手机号';
    return false;
  }
  errors.phone = '';
  return true;
}

function onFocus(field) {
  focused[field] = true;
}

function onBlur(field) {
  focused[field] = false;
}

async function nextStep() {
  errorMsg.value = ''
  if (loading.value) return
  // 校验
  const validName = validateName();
  const validEmail = validateEmail();
  const validPhone = validatePhone();
  if (!validName || !validEmail || !validPhone) return
  if (!form.resume) {
    errorMsg.value = '请上传简历';
    return
  }
  loading.value = true
  try {
    // 1. 注册用户
    const user = await apiFetch('/api/users', {
      method: 'POST',
      body: JSON.stringify({
        name: form.name,
        email: form.email,
        phoneNumber: form.phone
      })
    })
    userId.value = user.user_id
    // 2. 上传简历
    let resume
    try {
      resume = await uploadResume(userId.value, form.resume)
    } catch (e) {
      if (e.message && e.message.includes('409')) {
        alert('您已上传过相同内容的简历文件，请勿重复上传！')
        loading.value = false
        return
      }
      throw e
    }
    resumeId.value = resume.resume_id
    // 3. 提交职位申请
    await apiFetch('/api/applications', {
      method: 'POST',
      body: JSON.stringify({
        jobId: jobId.value,
        userId: userId.value,
        resumeId: resumeId.value
      })
    })
    alert('申请提交成功！')
    // 可跳转或重置表单
  } catch (e) {
    errorMsg.value = e.message || '提交失败，请重试';
  } finally {
    loading.value = false
  }
}

const steps = [
  { title: '提交申请' },
  { title: '线上面试' },
  { title: '完成申请' }
]
const activeStep = ref(0)
</script>

<style scoped>
:root {
  --theme-purple: #6B42DD;
}
.job-apply-bg {
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  min-height: 100vh;
  background: linear-gradient(180deg, #ecebfa 0%, #fff 100%);
  font-family: 'PingFang SC', 'Helvetica Neue', Arial, 'Microsoft YaHei', sans-serif;
  z-index: 0;
}
.job-apply-center {
  min-height: 100vh;
  width: 100vw;
  display: flex;
  justify-content: center;
  align-items: flex-start;
  position: relative;
  z-index: 1;
  padding-top: 56px;
}
.job-apply-card {
  margin: 0 auto;
  display: inline-flex;
  flex-direction: column;
  align-items: flex-end;
  gap: 12px;
  padding: 24px 48px;
  border-radius: 10px;
  border: 1px solid #D8D8D8;
  background: #FFF;
  box-shadow: 4px 4px 10px 0px rgba(0, 0, 0, 0.25);
  width: 502px;
  box-sizing: border-box;
}
.job-apply-header {
  display: flex;
  align-items: center;
  margin-bottom: 0;
  width: 406px;
  margin-left: auto;
  margin-right: auto;
}
.company-logo-box {
  width: 36px;
  height: 36px;
  flex-shrink: 0;
  border-radius: 10px;
  border: 1px solid #D8D8D8;
  background: url(/logo.png) lightgray 50% / cover no-repeat;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-right: 8px;
}
.company-name {
  color: #000;
  font-family: 'PingFang SC';
  font-size: 16px;
  font-style: normal;
  font-weight: 600;
  line-height: normal;
}
.job-title {
  color: #000;
  font-family: 'PingFang SC';
  font-size: 20px;
  font-style: normal;
  font-weight: 600;
  line-height: normal;
  margin-bottom: 0;
  width: 406px;
  margin-left: auto;
  margin-right: auto;
}
.job-tags-figma {
  display: flex;
  gap: 12px;
  margin-bottom: 0;
  width: 406px;
  margin-left: auto;
  margin-right: auto;
}
.tag-figma {
  display: inline-flex;
  align-items: center;
  background: #EEEEFF;
  border-radius: 999px;
  padding: 4px 8px;
  font-size: 14px;
  color: #6B42DD;
  font-weight: 500;
  font-family: 'PingFang SC', 'Helvetica Neue', Arial, 'Microsoft YaHei', sans-serif;
  line-height: 1;
  box-shadow: none; /* 如有阴影可按 Figma 补充 */
}
.tag-svg-icon {
  display: inline-flex;
  align-items: center;
  margin-right: 4px;
}
.tag-dot {
  display: inline-block;
  width: 8px;
  height: 8px;
  border-radius: 50%;
  background: var(--theme-purple);
  margin-right: 7px;
  vertical-align: middle;
}
.job-form-figma {
  width: 100%;
  display: flex;
  flex-direction: column;
  gap: 0;
  margin-top: 8px;
}
.form-group {
  display: flex;
  flex-direction: column;
  gap: 4px;
  gap: 6px;
  align-items: flex-start;
}
label {
  font-size: 14px;
  color: #222;
  font-weight: 500;
  margin-bottom: 0;
  width: 406px;
  text-align: left;
  margin-left: 0;
}
.required {
  color: var(--theme-purple);
  margin-left: 2px;
}
input[type="text"],
input[type="email"],
input[type="tel"] {
  display: flex;
  height: 36px;
  padding: 8px 12px;
  align-items: center;
  gap: 10px;
  align-self: stretch;
  border-radius: 6px;
  border: 1px solid #D8D8D8;
  font-size: 14px;
  font-family: 'PingFang SC', 'Helvetica Neue', Arial, 'Microsoft YaHei', sans-serif;
  background: #fff;
  color: #222;
  outline: none;
  transition: border 0.2s;
  width: 406px;
  box-sizing: border-box;
}
input:focus {
  border: 1.5px solid var(--theme-purple);
}
.resume-upload-box {
  border: 1.5px solid #D8D8D8;
  border-radius: 10px;
  background: #ffffff;
  min-height: 128px;
  height: 128px;
  width: 406px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  position: relative;
  transition: border 0.2s;
  margin-left: auto;
  margin-right: auto;
}
.resume-upload-box:hover {
  border-color: #6B42DD;
}
.resume-upload-content {
  display: flex;
  align-items: center;
  gap: 12px;
  margin-bottom: 4px;
}
.upload-icon {
  font-size: 20px;
  color: var(--theme-purple);
  font-weight: bold;
}
.upload-text {
  font-size: 15px;
  color: #6B42DD !important;
  font-weight: bold;
}
.upload-tip {
  font-size: 12px;
  color: #b0b0c0;
  margin-bottom: 2px;
}
.upload-filename-bold {
  font-size: 14px;
  color: #222;
  font-weight: 600;
  margin-right: 8px;
}
.form-btn-row {
  display: flex;
  justify-content: flex-end;
  margin-top: 24px;
}
.figma-btn {
  min-width: 96px;
  height: 38px;
  background: #222;
  color: #fff;
  border: none;
  border-radius: 10px;
  font-size: 17px;
  font-family: 'PingFang SC', 'Helvetica Neue', Arial, 'Microsoft YaHei', sans-serif;
  font-weight: 600;
  letter-spacing: 2px;
  cursor: pointer;
  transition: background 0.2s;
  box-shadow: 0 2px 8px 0 rgba(124,66,221,0.08);
  padding: 0 32px;
  align-self: flex-end;
}
.figma-btn:hover {
  background: #6B42DD !important;
  color: #fff;
}
.job-apply-steps-bar-figma {
  position: fixed;
  left: 0;
  bottom: 20px;
  width: 100vw;
  background: transparent;
  z-index: 10;
  padding-bottom: 0;
  box-sizing: border-box;
}
.steps-bar-inner-figma {
  display: flex;
  justify-content: center;
  align-items: flex-end;
  gap: 24px;
  width: calc(100vw - 64px);
  margin: 0 auto;
  position: relative;
  box-sizing: border-box;
}
.step-bar-block {
  display: flex;
  flex-direction: column-reverse;
  align-items: center;
}
.step-title-figma {
  font-size: 16px;
  font-family: 'PingFang SC', 'Helvetica Neue', Arial, 'Microsoft YaHei', sans-serif;
  color: #222;
  font-weight: 600;
  letter-spacing: 4px;
  margin-bottom: 0;
  margin-top: 8px;
  transition: color 0.2s;
}
.step-bar-progress-block {
  width: 448px;
  height: 8px;
  border-radius: 4px;
  background: #D9D9D9;
  transition: background 0.2s;
}
.step-bar-progress-block.active {
  background: #6B42DD !important;
}
.step-bar-block:first-child .step-bar-progress-block {
  background: #6B42DD !important;
}
@media (max-width: 512px) {
  .step-bar-progress-block {
    width: calc(100vw - 64px);
    min-width: 0;
    max-width: 100vw;
  }
}
.input-error {
  border: 1.5px solid #FF4848 !important;
  background: #fff;
}
.input-error-tip {
  display: flex;
  align-items: center;
  color: #FF4848;
  font-size: 14px;
  min-height: 20px;
  line-height: 1.5;
  font-weight: 400;
  gap: 4px;
  text-align: left;
  justify-content: flex-start;
  margin: 0;
}
.input-error-icon {
  display: flex;
  align-items: center;
  margin-right: 4px;
}
.input-focus {
  border: 1.5px solid #6B42DD !important;
  background: #fff;
}
.input-prefix-wrapper {
  position: relative;
  width: 406px;
}
.input-prefix-wrapper input[type="tel"] {
  padding-left: 38px;
}
.input-prefix {
  position: absolute;
  left: 12px;
  top: 50%;
  transform: translateY(-50%);
  color: #222;
  font-size: 14px;
  font-weight: 500;
  pointer-events: none;
}
.form-group + .form-group {
  margin-top: 20px;
}
.form-group:has(.input-error-tip:not(:empty)) + .form-group {
  margin-top: 4px;
}
.upload-change-btn {
  background: none;
  border: none;
  color: #6B42DD;
  font-size: 12px;
  font-weight: 400;
  cursor: pointer;
  padding: 0 4px;
}
.upload-change-btn:hover {
  text-decoration: underline;
}
</style> 