<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Passwordless Auth Logo</title>
    <style>
        body {
            margin: 0;
            padding: 40px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, sans-serif;
        }

        .container {
            text-align: center;
        }

        .logo-showcase {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 40px;
            margin-bottom: 40px;
        }

        .logo-card {
            background: white;
            padding: 30px;
            border-radius: 20px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2);
        }

        .logo-card h3 {
            margin-top: 20px;
            color: #333;
            font-size: 14px;
            text-transform: uppercase;
            letter-spacing: 1px;
        }

        /* Main Logo */
        .logo {
            width: 200px;
            height: 200px;
            margin: 0 auto;
            position: relative;
        }

        /* Logo 1: Shield with Key */
        .shield-logo {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            position: relative;
            overflow: hidden;
        }

        .shield-logo::before {
            content: '';
            position: absolute;
            width: 80px;
            height: 100px;
            background: rgba(255, 255, 255, 0.15);
            clip-path: polygon(50% 0%, 100% 25%, 100% 75%, 50% 100%, 0% 75%, 0% 25%);
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
        }

        .shield-logo .key-icon {
            width: 70px;
            height: 70px;
            position: relative;
            z-index: 1;
        }

        .key-icon .circle {
            width: 28px;
            height: 28px;
            border: 6px solid white;
            border-radius: 50%;
            position: absolute;
            top: 10px;
            left: 10px;
        }

        .key-icon .line {
            width: 6px;
            height: 30px;
            background: white;
            position: absolute;
            top: 32px;
            left: 21px;
            border-radius: 3px;
        }

        .key-icon .teeth {
            position: absolute;
            bottom: 8px;
            left: 18px;
        }

        .key-icon .tooth {
            width: 6px;
            height: 8px;
            background: white;
            display: inline-block;
            margin-right: 3px;
        }

        /* Logo 2: Fingerprint Style */
        .fingerprint-logo {
            background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .fingerprint-lines {
            width: 100px;
            height: 100px;
            position: relative;
        }

        .fp-line {
            position: absolute;
            border: 3px solid rgba(255, 255, 255, 0.8);
            border-radius: 50%;
        }

        .fp-line:nth-child(1) { width: 30px; height: 40px; top: 30px; left: 35px; }
        .fp-line:nth-child(2) { width: 50px; height: 60px; top: 20px; left: 25px; }
        .fp-line:nth-child(3) { width: 70px; height: 80px; top: 10px; left: 15px; }
        .fp-line:nth-child(4) { width: 90px; height: 100px; top: 0; left: 5px; opacity: 0.6; }

        /* Logo 3: Lock with OTP */
        .lock-logo {
            background: linear-gradient(135deg, #4facfe 0%, #00f2fe 100%);
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            position: relative;
        }

        .lock-body {
            width: 60px;
            height: 50px;
            background: white;
            border-radius: 8px;
            position: relative;
            margin-top: 15px;
        }

        .lock-shackle {
            width: 40px;
            height: 30px;
            border: 6px solid white;
            border-bottom: none;
            border-radius: 20px 20px 0 0;
            position: absolute;
            top: -25px;
            left: 10px;
        }

        .otp-dots {
            display: flex;
            justify-content: center;
            gap: 6px;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
        }

        .otp-dot {
            width: 6px;
            height: 6px;
            background: #4facfe;
            border-radius: 50%;
            animation: pulse 1.5s infinite;
        }

        .otp-dot:nth-child(2) { animation-delay: 0.2s; }
        .otp-dot:nth-child(3) { animation-delay: 0.4s; }
        .otp-dot:nth-child(4) { animation-delay: 0.6s; }

        @keyframes pulse {
            0%, 100% { opacity: 0.4; transform: scale(1); }
            50% { opacity: 1; transform: scale(1.3); }
        }

        /* Logo 4: Minimal Email */
        .email-logo {
            background: linear-gradient(135deg, #fa709a 0%, #fee140 100%);
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .envelope {
            width: 80px;
            height: 60px;
            position: relative;
        }

        .envelope-body {
            width: 80px;
            height: 50px;
            background: white;
            border-radius: 5px;
            position: relative;
        }

        .envelope-flap {
            width: 0;
            height: 0;
            border-left: 40px solid transparent;
            border-right: 40px solid transparent;
            border-top: 30px solid rgba(255, 255, 255, 0.9);
            position: absolute;
            top: 0;
            left: 0;
        }

        .security-badge {
            width: 30px;
            height: 30px;
            background: #fee140;
            border-radius: 50%;
            position: absolute;
            bottom: -10px;
            right: -10px;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 16px;
        }

        .download-section {
            background: white;
            padding: 30px;
            border-radius: 20px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2);
            margin-top: 20px;
        }

        .download-section h2 {
            margin-top: 0;
            color: #333;
        }

        .download-btn {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            border: none;
            padding: 15px 40px;
            border-radius: 50px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            margin: 10px;
            box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
            transition: transform 0.2s;
        }

        .download-btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 7px 20px rgba(102, 126, 234, 0.5);
        }

        .instructions {
            background: #f8f9fa;
            padding: 20px;
            border-radius: 10px;
            margin-top: 20px;
            text-align: left;
            color: #666;
            line-height: 1.8;
        }

        .instructions code {
            background: #e9ecef;
            padding: 2px 8px;
            border-radius: 4px;
            font-family: 'Courier New', monospace;
            color: #d63384;
        }

        canvas {
            display: none;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="logo-showcase">
            <div class="logo-card">
                <div class="logo shield-logo">
                    <div class="key-icon">
                        <div class="circle"></div>
                        <div class="line"></div>
                        <div class="teeth">
                            <div class="tooth"></div>
                            <div class="tooth"></div>
                            <div class="tooth"></div>
                        </div>
                    </div>
                </div>
                <h3>Shield + Key</h3>
            </div>

            <div class="logo-card">
                <div class="logo fingerprint-logo">
                    <div class="fingerprint-lines">
                        <div class="fp-line"></div>
                        <div class="fp-line"></div>
                        <div class="fp-line"></div>
                        <div class="fp-line"></div>
                    </div>
                </div>
                <h3>Fingerprint</h3>
            </div>

            <div class="logo-card">
                <div class="logo lock-logo">
                    <div class="lock-shackle"></div>
                    <div class="lock-body">
                        <div class="otp-dots">
                            <div class="otp-dot"></div>
                            <div class="otp-dot"></div>
                            <div class="otp-dot"></div>
                            <div class="otp-dot"></div>
                        </div>
                    </div>
                </div>
                <h3>Lock + OTP</h3>
            </div>

            <div class="logo-card">
                <div class="logo email-logo">
                    <div class="envelope">
                        <div class="envelope-body">
                            <div class="envelope-flap"></div>
                        </div>
                        <div class="security-badge">🔒</div>
                    </div>
                </div>
                <h3>Email Security</h3>
            </div>
        </div>

        <div class="download-section">
            <h2>📥 Download Your Logo</h2>
            <p>Choose your preferred design and download as PNG</p>
            <button class="download-btn" onclick="downloadLogo('shield', 0)">Shield + Key</button>
            <button class="download-btn" onclick="downloadLogo('fingerprint', 1)">Fingerprint</button>
            <button class="download-btn" onclick="downloadLogo('lock', 2)">Lock + OTP</button>
            <button class="download-btn" onclick="downloadLogo('email', 3)">Email Security</button>

            <div class="instructions">
                <h3>📁 How to Use in Your README:</h3>
                <ol>
                    <li>Create folder: <code>docs/images/</code> in your project root</li>
                    <li>Save downloaded logo as <code>logo.png</code></li>
                    <li>The README already includes the logo reference</li>
                    <li>Commit and push to GitHub</li>
                </ol>
                <p><strong>📌 Pro Tip:</strong> Use the <strong>Shield + Key</strong> logo for professional appearance or <strong>Lock + OTP</strong> to emphasize the OTP feature.</p>
            </div>
        </div>
    </div>

    <canvas id="canvas"></canvas>

    <script>
        function downloadLogo(name, index) {
            const canvas = document.getElementById('canvas');
            const ctx = canvas.getContext('2d');
            
            // Set canvas size
            canvas.width = 500;
            canvas.height = 500;

            // Get the logo element
            const logos = document.querySelectorAll('.logo');
            const logo = logos[index];

            // Create gradient backgrounds
            const gradients = [
                ['#667eea', '#764ba2'], // Shield
                ['#f093fb', '#f5576c'], // Fingerprint
                ['#4facfe', '#00f2fe'], // Lock
                ['#fa709a', '#fee140']  // Email
            ];

            const gradient = ctx.createLinearGradient(0, 0, 500, 500);
            gradient.addColorStop(0, gradients[index][0]);
            gradient.addColorStop(1, gradients[index][1]);

            // Draw circle background
            ctx.fillStyle = gradient;
            ctx.beginPath();
            ctx.arc(250, 250, 250, 0, Math.PI * 2);
            ctx.fill();

            // Draw logo elements based on type
            ctx.fillStyle = 'white';
            ctx.strokeStyle = 'white';
            ctx.lineWidth = 8;

            if (name === 'shield') {
                // Shield outline
                ctx.globalAlpha = 0.15;
                ctx.beginPath();
                ctx.moveTo(250, 150);
                ctx.lineTo(320, 190);
                ctx.lineTo(320, 290);
                ctx.lineTo(250, 350);
                ctx.lineTo(180, 290);
                ctx.lineTo(180, 190);
                ctx.closePath();
                ctx.fill();
                ctx.globalAlpha = 1;

                // Key
                ctx.beginPath();
                ctx.arc(250, 220, 25, 0, Math.PI * 2);
                ctx.stroke();
                ctx.fillRect(244, 240, 12, 50);
                ctx.fillRect(244, 280, 12, 15);
                ctx.fillRect(250, 280, 12, 15);
                ctx.fillRect(256, 280, 12, 15);
            } else if (name === 'fingerprint') {
                // Fingerprint lines
                ctx.lineWidth = 6;
                for (let i = 0; i < 4; i++) {
                    ctx.globalAlpha = 1 - (i * 0.15);
                    ctx.beginPath();
                    ctx.ellipse(250, 250, 40 + (i * 25), 50 + (i * 25), 0, 0, Math.PI * 2);
                    ctx.stroke();
                }
                ctx.globalAlpha = 1;
            } else if (name === 'lock') {
                // Lock shackle
                ctx.lineWidth = 10;
                ctx.beginPath();
                ctx.arc(250, 230, 40, Math.PI, 0, false);
                ctx.stroke();

                // Lock body
                ctx.fillRect(210, 260, 80, 70);
                ctx.beginPath();
                ctx.arc(210, 260, 10, Math.PI/2, Math.PI);
                ctx.arc(210, 330, 10, Math.PI, Math.PI*1.5);
                ctx.arc(290, 330, 10, Math.PI*1.5, 0);
                ctx.arc(290, 260, 10, 0, Math.PI/2);
                ctx.fill();

                // OTP dots
                ctx.fillStyle = gradients[index][0];
                for (let i = 0; i < 4; i++) {
                    ctx.beginPath();
                    ctx.arc(220 + (i * 20), 295, 5, 0, Math.PI * 2);
                    ctx.fill();
                }
            } else if (name === 'email') {
                // Envelope body
                ctx.fillRect(200, 230, 100, 70);
                
                // Envelope flap
                ctx.beginPath();
                ctx.moveTo(200, 230);
                ctx.lineTo(250, 270);
                ctx.lineTo(300, 230);
                ctx.closePath();
                ctx.fill();

                // Security badge
                ctx.fillStyle = gradients[index][1];
                ctx.beginPath();
                ctx.arc(310, 310, 25, 0, Math.PI * 2);
                ctx.fill();
                
                // Lock emoji
                ctx.fillStyle = 'white';
                ctx.font = 'bold 24px Arial';
                ctx.textAlign = 'center';
                ctx.textBaseline = 'middle';
                ctx.fillText('🔒', 310, 310);
            }

            // Download
            canvas.toBlob(function(blob) {
                const url = URL.createObjectURL(blob);
                const a = document.createElement('a');
                a.href = url;
                a.download = `passwordless-auth-${name}-logo.png`;
                document.body.appendChild(a);
                a.click();
                document.body.removeChild(a);
                URL.revokeObjectURL(url);
            });
        }
    </script>
</body>
</html>
