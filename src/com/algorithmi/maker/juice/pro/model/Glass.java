/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
  * copyright (c)2013-2014 Algorithmi.
  *
  * @author muneebahmad (ahmadgallian@yahoo.com) 
  *
  * The following source - code IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
  * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
  * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
  * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
  * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
  * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
  * THE SOFTWARE.
  * **/
package com.algorithmi.maker.juice.pro.model;


/**
 *
 * @author muneebahmad
 */
public class Glass extends MObject {
    private String packageId;
    
    public Glass(String id, String name, String imageName, boolean isLocked,
			float paramFloat, String packageId) {
        super(id, name, imageName, isLocked);
        this.packageId = packageId;
    }
    
    /**
     * 
     * @return String 
     */
    public String getPackageId() {
        return this.packageId;
    }
    
    /**
     * 
     * @param packageId String. 
     */
    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }
} /** end class. */
